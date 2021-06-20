package com.cars.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Address;
import com.cars.beans.Appointment;
import com.cars.beans.Card;
import com.cars.beans.Customer;
import com.cars.beans.Payment;
import com.cars.dao.IAppointmentRepository;

@Service
public class AppointmentService implements IAppointmentService {
	@Autowired
	IAppointmentRepository repo;
	
	String phn = "[6-9][0-9]{9}";
	Pattern phone=Pattern.compile(phn);
	Pattern email1 = Pattern.compile("[a-zA-Z_]+[0-9]*[@][a-zAZ]+[/.][a-zA-Z]{2,3}");
	Pattern p2 = Pattern.compile("^\\d{16}$");
	Pattern pc = Pattern.compile("[0-9]{3}$");

	@Override
	public Appointment addAppointment(Appointment appointment) throws ResourceNotFoundException {
		long id=appointment.getAppointmentId();
		Optional<Appointment> s=repo.findById(id);
		Customer customer=appointment.getCustomer();
		String e=customer.getEmail();
		String ph=customer.getContactNo();
		
		Payment payment=appointment.getPayment();
		List<Card> np1=payment.getCard();
		Card np2=np1.get(0);
		String np=np2.getCardNumber();
		String np3=np2.getCvv();
		
		if(s.isPresent()) {
		throw new ResourceNotFoundException(id +" is already present in the database");
		}
		if(email1.matcher(e).matches())
		{
			if(phone.matcher(ph).matches())
			{
				if(p2.matcher(np).matches())
				{
					if(pc.matcher(np3).matches())
					{
						repo.save(appointment);
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
		return appointment;
	}

	@Override
	public String removeAppointmentbyId(long id) throws Exception {
		Supplier<Exception> s = () -> new ResourceNotFoundException("Appointment Id is not present in the database");
		repo.findById(id).orElseThrow(s);
		repo.deleteById(id);
		return "Deleted";
	}

	@Override
	public Appointment updateAppointment(long id, Appointment appointment) throws Exception {
		Supplier<Exception> s = () -> new ResourceNotFoundException("Appointment Id is not present in the database");
		Appointment a1=repo.findById(id).orElseThrow(s);
		Customer customer=appointment.getCustomer();
		String e=customer.getEmail();
		String ph=customer.getContactNo();
		
		Payment payment=appointment.getPayment();
		List<Card> np1=payment.getCard();
		Card np2=np1.get(0);
		String np=np2.getCardNumber();
		String np3=np2.getCvv();
		Customer c1=a1.getCustomer();
		Address a=c1.getAddress();
		long asid=a.getAid();
		Customer cu=appointment.getCustomer();
		Address a2=cu.getAddress();
		long acid=a2.getAid();
		
		if(asid==acid)
		{
		a1.setCustomer(appointment.getCustomer());
		a1.setInspectionType(appointment.getInspectionType());
		a1.setLocation(appointment.getLocation());
		a1.setPayment(appointment.getPayment());
		a1.setPreferredDate(appointment.getPreferredDate());
		a1.setPreferredTime(appointment.getPreferredTime());
		if(email1.matcher(e).matches())
		{
			if(phone.matcher(ph).matches())
			{
				if(p2.matcher(np).matches())
				{
					if(pc.matcher(np3).matches())
					{
						repo.save(a1);
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
		return a1;
	}

	@Override
	public Optional<Appointment> getAppointment(long id) throws Exception {
		
		Supplier<Exception> s = () -> new ResourceNotFoundException("Appointment Id is not present in the database");
		Optional<Appointment> a=Optional.of(repo.findById(id).orElseThrow(s));
		return a;
	}

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> le = repo.findAll();
		return le;
	}

	@Override
	public List<Appointment> getOpenAppointments() {
		return null;
	}

	
	
}
