package com.cars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Address;
import com.cars.beans.Customer;
import com.cars.dao.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService {

	List<Customer> le;

	@Autowired
	ICustomerRepository icrepo;
	
	String phn = "[6-9][0-9]{9}";
	Pattern phone=Pattern.compile(phn);
	
	Pattern email1 = Pattern.compile("[a-zA-Z_]+[0-9]*[@][a-zAZ]+[/.][a-zA-Z]{2,3}");
	
	
	@Override
	public List<Customer> getAllCustomers() {
		le = new ArrayList<>();
		le= icrepo.findAll();
		return le;
	}
	
	@Override
	public Customer addCustomer(Customer customer) throws ResourceNotFoundException {
		String e=customer.getEmail();
		String ph=customer.getContactNo();
		long id=customer.getUserId();
		Optional<Customer> s=icrepo.findById(id);
		if(s.isPresent()) {
		throw new ResourceNotFoundException(id +" is already present in the database");
		}
		if(email1.matcher(e).matches())
		{
			if(phone.matcher(ph).matches())
			{
				icrepo.save(customer);
			}
			else
			{
				throw new ResourceNotFoundException("Enter a 10 digit valid phn number which starts in between [6-9] ");
			}
		}
		else
		{
		throw new ResourceNotFoundException("Enter a valid Email id..");
		}
		return customer;
	}
	
	@Override
	public Optional<Customer> getCustomer(long uid) throws Exception {
		Supplier<Exception> s = () -> new ResourceNotFoundException("Customer Id is not present in the database");
		Optional<Customer> e1 = Optional.of(icrepo.findById(uid).orElseThrow(s));
		return e1;
	}
	
	@Override
	public Customer updateCustomer(Customer customer) throws Exception  {
		String e=customer.getEmail();
		String ph=customer.getContactNo();
		long uid=customer.getUserId();
		System.out.println(customer.getName());
		Supplier<Exception> s = () -> new ResourceNotFoundException("Customer Id is not present in the database");
		Customer c1=icrepo.findById(uid).orElseThrow(s);
		Address a=c1.getAddress();
		long asid=a.getAid();
		Address a2=customer.getAddress();
		long acid=a2.getAid();

		if(asid==acid)
		{
		c1.setName(customer.getName());
		c1.setAddress(customer.getAddress());
		c1.setContactNo(customer.getContactNo());
		c1.setDob(customer.getDob());
		c1.setEmail(customer.getEmail());
		if(email1.matcher(e).matches())
		{
			if(phone.matcher(ph).matches())
			{
				icrepo.save(c1);
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
		else {
			throw new ResourceNotFoundException("Address id is not same as  in the customer details plz check and give.");
		}
		return c1;
	}
	

	@Override
	public String removeCustomer(long userId) throws Exception {
		Supplier<Exception> s = () -> new ResourceNotFoundException("Customer Id is not present in the database");
		icrepo.findById(userId).orElseThrow(s);
		icrepo.deleteById(userId);
		return "Deleted";
	}
	 
	@Override
	public List<Customer> getCustomersByLocation(String city) {
		List<Customer> ls = icrepo.findByCitySorted(city);
		System.out.println(ls);
		return ls;
	}

}
