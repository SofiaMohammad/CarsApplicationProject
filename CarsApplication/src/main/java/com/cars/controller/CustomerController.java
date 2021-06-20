package com.cars.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Customer;
import com.cars.service.CustomerService;

@Controller
@RestController
@RequestMapping(path="/api")
public class CustomerController {
	
	@Autowired
	CustomerService cusservice;
	
	@GetMapping(path="/getCustomers")
	public ResponseEntity<List<Customer>> getCustomers()
	{
		List<Customer> le=this.cusservice.getAllCustomers();
		
		ResponseEntity<List<Customer>> re=new ResponseEntity<List<Customer>>(le,HttpStatus.OK);
		return re;
	}
	
	@GetMapping(path="/getCustomer/{userId}")
	public ResponseEntity<Optional<Customer>> getCustomerbyId(@PathVariable long userId) throws Exception
	{
		Optional<Customer> e1=cusservice.getCustomer(userId);
		
		ResponseEntity<Optional<Customer>> re=new ResponseEntity<Optional<Customer>>(e1,HttpStatus.OK);
		return re;
	}


	@PostMapping(path="/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer e) throws ResourceNotFoundException
	{
		Customer e1=this.cusservice.addCustomer(e);
		
		ResponseEntity<Customer> re=new ResponseEntity<Customer>(e1,HttpStatus.OK);
		return re;
	}
	
	@PutMapping(path="/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws Exception 
	{
		Customer e1=cusservice.updateCustomer(customer);
		
		ResponseEntity<Customer> re=new ResponseEntity<Customer>(e1,HttpStatus.OK);
		return re;
	}
	
	  @DeleteMapping(path="/removeCustomer/{userId}") 
	  public ResponseEntity<String>removeCustomer(@PathVariable long userId) throws Exception { 
		  cusservice.removeCustomer(userId);
	  
	  ResponseEntity<String> re=new ResponseEntity<String>("Deleted",HttpStatus.OK); 
	  return re; 
	  }
	  
	@GetMapping(path = {"/getCustomersByLocation/{city}"}
	    )
	    public ResponseEntity<List<Customer>> getCustomersByLoc(@PathVariable String city) {
	        List<Customer> le = this.cusservice.getCustomersByLocation(city);
	        ResponseEntity<List<Customer>> re = new ResponseEntity<List<Customer>>(le, HttpStatus.OK);
	        return re;
	    }
	
	}
