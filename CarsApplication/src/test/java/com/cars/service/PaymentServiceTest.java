package com.cars.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Card;
import com.cars.beans.Payment;
import com.cars.dao.IPaymentRepository;


@SpringBootTest
class PaymentServiceTest {
	
	@Autowired
	PaymentService payservice;
	@MockBean
	IPaymentRepository repo;
	
	@Test
	void testaddPayment() throws ResourceNotFoundException {
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
	
		Mockito.when(repo.save(p)).thenReturn(p);
		assertThat(payservice.addPayment(p)).isEqualTo(p);
	}
	@Test
	void testremovePayment()
	{
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
		
		Optional<Payment> u1=Optional.of(p);
		Mockito.when(repo.findById((long) 10)).thenReturn(u1);
		 Mockito.when(repo.existsById(p.getPaymentId())).thenReturn(false);
		   assertFalse(repo.existsById(p.getPaymentId()));
		
	}
	
	@Test
	void testupdate() throws Exception {
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
		
		Optional<Payment> p1=Optional.of(p);
		Mockito.when(repo.findById((long) 10)).thenReturn(p1);
		p.setAmount(200000);
		Mockito.when(repo.save(p)).thenReturn(p);
		assertThat(payservice.updatePayment(p)).isEqualTo(p);
	}
	
	@Test
	void testgetPaymentDetails() throws Exception {
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
		
		Optional<Payment> p1=Optional.of(p);
		   Mockito.when(repo.findById((long) 10)).thenReturn(p1);
		   Mockito.when(repo.getId(p.getPaymentId())).thenReturn(p1);

		   assertThat(payservice.getPaymentDetails(p.getPaymentId())).isEqualTo(p1);
	}
	
	@Test
	void testgetAllPaymentDetails() throws Exception {
		List<Card> cs=new ArrayList<>();
		Card ca=new Card();
		ca.setCardExpiry("2021/12");
		ca.setCardName("Sunil Kumar");
		ca.setCardNumber("1234567891234567");
		ca.setCvv("054");
		cs.add(ca);
		
		List<Payment> p=new ArrayList<>();
		Payment p1=new Payment();
		p1.setAmount(1000);
		p1.setCard(cs);
		p1.setPaymentId(10);
		p1.setStatus("Active");
		p1.setType1("single");
		
		Payment p2=new Payment();
		p2.setAmount(1000);
		p2.setCard(cs);
		p2.setPaymentId(12);
		p2.setStatus("Active");
		p2.setType1("single");
		
		p.add(p1);
		p.add(p2);
		
		Mockito.when(repo.findByPaymentIdSorted()).thenReturn(p);
		
		assertThat(payservice.getAllPaymentDetails()).isEqualTo(p);
	
	}
}
