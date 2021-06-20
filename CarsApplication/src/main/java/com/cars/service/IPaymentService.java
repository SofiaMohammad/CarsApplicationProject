package com.cars.service;

import java.util.List;
import java.util.Optional;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Payment;

public interface IPaymentService {
	public Payment addPayment(Payment payment) throws ResourceNotFoundException;
	public Payment updatePayment(Payment payment) throws Exception;
	public Optional<Payment> getPaymentDetails(long id) throws Exception;
	public List<Payment> getAllPaymentDetails();
	public String removePayment(long pid) throws ResourceNotFoundException;
	
}
