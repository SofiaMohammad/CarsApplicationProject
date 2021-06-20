package com.cars.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Card;
import com.cars.beans.Payment;
import com.cars.dao.IPaymentRepository;

@Service
public class PaymentService implements IPaymentService {
	@Autowired
	IPaymentRepository repo;
	// Payment pay;
		
 Pattern p2 = Pattern.compile("^\\d{16}$");
	 
	 Pattern pc = Pattern.compile("[0-9]{3}$");

	@Override
	public Payment addPayment(Payment payment) throws ResourceNotFoundException {
		List<Card> np1=payment.getCard();
		Card np2=np1.get(0);
		String np=np2.getCardNumber();
		String np3=np2.getCvv();
		long id=payment.getPaymentId();
		Optional<Payment> s=repo.findById(id);
		if(s.isPresent())
		{
		throw new ResourceNotFoundException(id +" is already present in the database");
		}
		if(p2.matcher(np).matches())
		{
			if(pc.matcher(np3).matches())
			{
				repo.save(payment);
			}
			else
			{
				throw new ResourceNotFoundException("cvv must be 3 digits");
			}
		}
		else
		{
		throw new ResourceNotFoundException("Card id must be 16 digits ");
		}
		return payment;
	}

	@Override
	public String removePayment(long pid) throws ResourceNotFoundException  {
		//Supplier<Exception> s = () -> new ResourceNotFoundException("Payment Id is not present in the database");
		Optional<Payment> p1=repo.findById(pid);
		if(p1.isPresent())
		{
		repo.deleteById(pid);
		return "Deleted";
		}
		else 
		{
			throw new ResourceNotFoundException(pid +" is not present in the database");
		}		
	}


	@Override
	public Payment updatePayment(Payment payment) throws Exception {
		long pid = payment.getPaymentId();
		Supplier<Exception> s = () -> new ResourceNotFoundException(pid +" is not present in the database");

		Payment p1 = repo.findById(pid).orElseThrow(s);
		p1.setCard(payment.getCard());
		List<Card> np1=payment.getCard();
		Card np2=np1.get(0);
		String np=np2.getCardNumber();
		String np3=np2.getCvv();
		
		if(p2.matcher(np).matches()) 
		{
			if(pc.matcher(np3).matches())
			{	
				p1.setType1(payment.getType1());
				p1.setStatus(payment.getStatus());
				p1.setAmount(payment.getAmount());
				repo.save(p1);
			}
			else
			{
				throw new ResourceNotFoundException("cvv must be 3 digits");
			}
		}
		else
		{
		throw new ResourceNotFoundException("Card id must be 16 digits ");
		}
		return p1;
	}

	@Override
	public Optional<Payment> getPaymentDetails(long id) throws Exception {
		Supplier<Exception> s = () -> new ResourceNotFoundException(id+" is not present in the database");
		Optional<Payment> p = Optional.of(repo.findById(id).orElseThrow(s));
		return p;
	}
	
	List<Payment> p3;

	@Override
	public List<Payment> getAllPaymentDetails() {
		//pl = new ArrayList<>();
		//pl = repo.findAll();
		p3=repo.findByPaymentIdSorted();
		/*
		 * p2=new ArrayList<>(); p2=repo.findByPaymentIdSorted(p1.getpaymentId());
		 */
		return p3;
	}

}
