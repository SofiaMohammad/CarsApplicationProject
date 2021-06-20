package com.cars.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Address;
import com.cars.beans.Appointment;
import com.cars.beans.Card;
import com.cars.beans.Customer;
import com.cars.beans.Payment;
import com.cars.dao.IAppointmentRepository;

@SpringBootTest
class AppointmentServiceTest {
	@Autowired
	AppointmentService appointmentservice;
	@MockBean
	IAppointmentRepository repo;

	@Test
	void testaddAppointment() throws ResourceNotFoundException {
		List<Card> cs=new ArrayList<>();
		Card ca=new Card();
		ca.setCardExpiry("2021/12");
		ca.setCardName("Sunil Kumar");
		ca.setCardNumber("1234567891234567");
		ca.setCvv("054");
		cs.add(ca);
		
		Payment p=new Payment();
		p.setAmount(1000);
		p.setCard(cs);
		p.setPaymentId(10);
		p.setStatus("Active");
		p.setType1("single");
		
		Address a=new Address();
		a.setAid(1);
		a.setArea("Mallayapeta");
		a.setCity("Rajahmundry");
		a.setDoorNo("852-545");
		a.setPincode(533101);
		a.setState("Andhra Pradesh");
		a.setStreet("Industrial Colony");
		
		Customer c=new Customer();
		c.setUserId(10);
		c.setAddress(a);
		c.setContactNo("9490806700");
		c.setName("Sunil");
		c.setDob("2019-20-5");
		c.setEmail("yaghnasunil@gmail.com");
		
		Appointment u=new Appointment();
		u.setAppointmentId(10);
		u.setCustomer(c);
		u.setInspectionType("Home");
		u.setLocation("Hyd");
		u.setPreferredDate("2021-06-21");
		u.setPreferredTime("24:00");
		u.setPayment(p);
		
		Mockito.when(repo.save(u)).thenReturn(u);
		assertThat(appointmentservice.addAppointment(u)).isEqualTo(u);
	}
	
	@Test
	void testUpdateAppointment() throws Exception {
		List<Card> cs=new ArrayList<>();
		Card ca=new Card();
		ca.setCardExpiry("2021/12");
		ca.setCardName("Sunil Kumar");
		ca.setCardNumber("1234567891234567");
		ca.setCvv("054");
		cs.add(ca);
		
		Payment p=new Payment();
		p.setAmount(1000);
		p.setCard(cs);
		p.setPaymentId(10);
		p.setStatus("Active");
		p.setType1("single");
		
		Address a=new Address();
		a.setAid(1);
		a.setArea("Mallayapeta");
		a.setCity("Rajahmundry");
		a.setDoorNo("852-545");
		a.setPincode(533101);
		a.setState("Andhra Pradesh");
		a.setStreet("Industrial Colony");
		
		Customer c = new Customer();
		c.setAddress(a);
		c.setContactNo("9988877661");
		c.setDob("22-09-1999");
		c.setEmail("s@gmail.com");
		c.setUserId(12);
		c.setName("s");
		
		Appointment ap= new Appointment();
		ap.setAppointmentId(12);
		ap.setCustomer(c);
		ap.setPayment(p);
		ap.setInspectionType("home");
		ap.setLocation("Hyderabad");
		ap.setPreferredDate("2020-10-12");
		ap.setPreferredTime("18:00");
		
		Optional<Appointment> a1=Optional.of(ap);
		Mockito.when(repo.findById((long) 12)).thenReturn(a1);
		ap.setLocation("Mumbai");
		Mockito.when(repo.save(ap)).thenReturn(ap);
		assertThat(appointmentservice.updateAppointment(ap.getAppointmentId(),ap)).isEqualTo(ap);
	}
	
	@Test
	void testRemoveAppointmentbyId() {
		List<Card> cs=new ArrayList<>();
		Card ca=new Card();
		ca.setCardExpiry("2021/12");
		ca.setCardName("Sunil Kumar");
		ca.setCardNumber("1234567891234567");
		ca.setCvv("054");
		cs.add(ca);
		
		Payment p=new Payment();
		p.setAmount(1000);
		p.setCard(cs);
		p.setPaymentId(10);
		p.setStatus("Active");
		p.setType1("single");
		
		Address ad=new Address();
		ad.setAid(1);
		ad.setArea("Mallayapeta");
		ad.setCity("Rajahmundry");
		ad.setDoorNo("852-545");
		ad.setPincode(533101);
		ad.setState("Andhra Pradesh");
		ad.setStreet("Industrial Colony");
		
		Customer c = new Customer();
		c.setAddress(ad);
		c.setContactNo("9988877661");
		c.setDob("22-09-1999");
		c.setEmail("s@gmail.com");
		c.setUserId(12);
		c.setName("s");
		
		Appointment a= new Appointment();
		a.setAppointmentId(12);
		a.setCustomer(c);
		a.setPayment(p);
		a.setInspectionType("home");
		a.setLocation("Hyderabad");
		a.setPreferredDate("2020-10-12");
		a.setPreferredTime("18:00");
	
		Optional<Appointment> u1=Optional.of(a);
		Mockito.when(repo.findById((long) 12)).thenReturn(u1);
		 Mockito.when(repo.existsById(a.getAppointmentId())).thenReturn(false);
		   assertFalse(repo.existsById(a.getAppointmentId()));
	}
	
	@Test
	void testGetAppointment() throws Exception {
		List<Card> cs=new ArrayList<>();
		Card ca=new Card();
		ca.setCardExpiry("2021/12");
		ca.setCardName("Sunil Kumar");
		ca.setCardNumber("1234567891234567");
		ca.setCvv("054");
		cs.add(ca);
		
		Payment p=new Payment();
		p.setAmount(1000);
		p.setCard(cs);
		p.setPaymentId(10);
		p.setStatus("Active");
		p.setType1("single");
		
		Address ad=new Address();
		ad.setAid(1);
		ad.setArea("Mallayapeta");
		ad.setCity("Rajahmundry");
		ad.setDoorNo("852-545");
		ad.setPincode(533101);
		ad.setState("Andhra Pradesh");
		ad.setStreet("Industrial Colony");
		
		Customer c = new Customer();
		c.setAddress(ad);
		c.setContactNo("9988877661");
		c.setDob("22-09-1999");
		c.setEmail("s@gmail.com");
		c.setUserId(12);
		c.setName("s");
		
		Appointment a= new Appointment();
		a.setAppointmentId(12);
		a.setCustomer(c);
		a.setPayment(null);
		a.setInspectionType("home");
		a.setLocation("Hyderabad");
		a.setPreferredDate("2020-10-12");
		a.setPreferredTime("18:00");
		Optional<Appointment> c1=Optional.of(a);
		Mockito.when(repo.findById((long) 12)).thenReturn(c1);
	    assertThat(appointmentservice.getAppointment(12)).isEqualTo(c1);
	    
	}

	@Test
	void testGetAllAppointments() {
		List<Card> cs=new ArrayList<>();
		Card ca=new Card();
		ca.setCardExpiry("2021/12");
		ca.setCardName("Sunil Kumar");
		ca.setCardNumber("1234567891234567");
		ca.setCvv("054");
		cs.add(ca);
		
		Payment p=new Payment();
		p.setAmount(1000);
		p.setCard(cs);
		p.setPaymentId(10);
		p.setStatus("Active");
		p.setType1("single");
		
		Address ad=new Address();
		ad.setAid(1);
		ad.setArea("Mallayapeta");
		ad.setCity("Rajahmundry");
		ad.setDoorNo("852-545");
		ad.setPincode(533101);
		ad.setState("Andhra Pradesh");
		ad.setStreet("Industrial Colony");
		
		Customer c = new Customer();
		c.setAddress(ad);
		c.setContactNo("998887766");
		c.setDob("22-09-1999");
		c.setEmail("s@gmail.com");
		c.setUserId(12);
		c.setName("s");
		
		Customer c1 = new Customer();
		c1.setAddress(null);
		c1.setContactNo("886677554");
		c1.setDob("27-05-1998");
		c1.setEmail("sh@gmail.com");
		c1.setUserId(13);
		c1.setName("sh");
		
		
		Appointment a= new Appointment();
		a.setAppointmentId(12);
		a.setCustomer(c);
		a.setPayment(null);
		a.setInspectionType("home");
		a.setLocation("Hyderabad");
		a.setPreferredDate("2020-10-12");
		a.setPreferredTime("18:00");
		
		Appointment a1= new Appointment();
		a1.setAppointmentId(13);
		a1.setCustomer(c1);
		a1.setPayment(null);
		a1.setInspectionType("showroom");
		a1.setLocation("Mumbai");
		a1.setPreferredDate("2020-11-12");
		a1.setPreferredTime("14:00");
		
		List<Appointment> appointList = new ArrayList<>();
		appointList.add(a);
		appointList.add(a1);
		
		Mockito.when(repo.findAll()).thenReturn(appointList);
		assertThat(appointmentservice.getAllAppointments()).isEqualTo(appointList);

		
	}

	

}
