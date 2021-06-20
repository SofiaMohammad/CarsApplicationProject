package com.cars.service;

import java.util.List;
import java.util.Optional;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Customer;

public interface ICustomerService {

	public Customer addCustomer(Customer customer) throws ResourceNotFoundException;
	public String removeCustomer(long custId) throws Exception;
	public Optional<Customer> getCustomer(long custId) throws Exception;
	public List<Customer> getAllCustomers(); 
	public Customer updateCustomer(Customer customer) throws Exception;
	public List<Customer> getCustomersByLocation(String city);
}
