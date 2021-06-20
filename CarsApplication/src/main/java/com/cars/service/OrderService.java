package com.cars.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Address;
import com.cars.beans.Customer;
import com.cars.beans.Order;
import com.cars.dao.IOrderRepository;

@Service
public class OrderService implements IOrderService {
	
@Autowired 
IOrderRepository repo;

String phn = "[6-9][0-9]{9}";
Pattern phone=Pattern.compile(phn);
Pattern email1 = Pattern.compile("[a-zA-Z_]+[0-9]*[@][a-zAZ]+[/.][a-zA-Z]{2,3}");

	@Override
	public Order addOrder(Order order) throws ResourceNotFoundException {
		long id=order.getOrderId();
		Customer customer=order.getCustomer();
		String e=customer.getEmail();
		String ph=customer.getContactNo();
		String payMethod=order.getPaymentMethod();
		Optional<Order> s1=repo.findById(id);
		
		if(s1.isPresent()) {
			throw new ResourceNotFoundException(id +" is already present in the database");
		}
		if(email1.matcher(e).matches())
		{
			if(phone.matcher(ph).matches())
			{	
				if(payMethod.equals("card") || payMethod.equals("cash"))
				{
				repo.save(order);
				}
				else
				{
				throw new ResourceNotFoundException("Enter a valid paymethod either 'cash' or 'card'");
				}

			}
			else
			{
				throw new ResourceNotFoundException("Enter a 10 digit valid phn number which starts in between [6-9]");
			}
		}
		else
		{
		throw new ResourceNotFoundException("Enter a valid Email id..");
		}
		return order;
	}

	@Override
	public String removeOrder(long id) throws Exception {
		Supplier<Exception> s = () -> new ResourceNotFoundException("Order Id is not present in the database");
		repo.findById(id).orElseThrow(s);
		repo.deleteById(id);
		return "Deleted" ;
	}

	@Override
	public Order updateOrder(long id, Order order) throws Exception {
		Supplier<Exception> s = () -> new ResourceNotFoundException("Order Id is not present in the database");
		Order s1=repo.findById(id).orElseThrow(s);
		
		Customer customer=order.getCustomer();
		String e=customer.getEmail();
		String ph=customer.getContactNo();
		String payMethod=order.getPaymentMethod();
		Customer c=s1.getCustomer();
		Address a=c.getAddress();
		long asid=a.getAid();
		
		Customer cus=order.getCustomer();
		Address a2=cus.getAddress();
		long acid=a2.getAid(); 
		if(asid==acid)
		{
		s1.setAmount(order.getAmount());
		s1.setBillingDate(order.getBillingDate());
		s1.setCustomer(order.getCustomer());
		if(email1.matcher(e).matches())
		{
			if(phone.matcher(ph).matches())
			{	
				if(payMethod.equals("card") || payMethod.equals("cash"))
				repo.save(s1);
				else
				throw new ResourceNotFoundException("Enter a valid paymethod either 'cash' or 'card'");

			}
			else
			{
				throw new ResourceNotFoundException("Enter a 10 digit valid phn number which starts in between [6-9]");
			}
		}
		else
		{
		throw new ResourceNotFoundException("Enter a valid Email id..");
		}
		}
		return s1;
	}

	@Override
	public Order getOrderDetails(long id) throws Exception {
		Supplier<Exception> s = () -> new ResourceNotFoundException("Order Id is not present in the database");
		Order s1=repo.findById(id).orElseThrow(s);
		return s1;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> le = repo.findAll();
		return le;
	}

}
