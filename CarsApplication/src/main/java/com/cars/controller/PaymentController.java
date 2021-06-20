package com.cars.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Payment;
import com.cars.service.PaymentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path="/api")
public class PaymentController {
	/*
	 * @RequestMapping("welcome") public String welcome() {
	 * System.out.println("Welcome to Spring Data JPA Rest"); return
	 * "Welcome to Spring Data JPA Rest"; }
	 */
	@Autowired
	PaymentService paymentservice;
	
	@PostMapping(path="/Payment/addPayment")
	public Payment addPayment(@RequestBody Payment payment) throws ResourceNotFoundException{
		Payment p=paymentservice.addPayment(payment);
		return p;
	}
	
	@DeleteMapping(path="/deletePayment/{paymentId}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable long paymentId) throws ResourceNotFoundException
	{
		paymentservice.removePayment(paymentId);
		ResponseEntity<String> re=new ResponseEntity<String>("Deleted",HttpStatus.OK);
		return re;
	}
	
	@PutMapping(path="/updatePayment/{paymentId}")
	public ResponseEntity<Payment> updatePaymentById(@RequestBody Payment p) throws Exception 
	{
		Payment p1=paymentservice.updatePayment(p);
		ResponseEntity<Payment> re=new ResponseEntity<Payment>(p1,HttpStatus.OK);
		return re;
	}
	
	@GetMapping(path="/getPaymentDetails/{paymentId}")
	public ResponseEntity<Optional<Payment>> getEmpById(@PathVariable long paymentId) throws Exception
	{
		Optional<Payment> p1=paymentservice.getPaymentDetails(paymentId);		
		ResponseEntity<Optional<Payment>> re=new ResponseEntity<Optional<Payment>>(p1,HttpStatus.OK);
		return re;
	}

	@GetMapping(path="/getAllPaymentDetails")
	public ResponseEntity<List<Payment>> getAllPaymentDetails()
	{
		List<Payment> pl=paymentservice.getAllPaymentDetails();
		ResponseEntity<List<Payment>> re=new ResponseEntity<List<Payment>>(pl,HttpStatus.OK);
		return re;
	}
}

/*
 * @GetMapping(path="/getAllPaymentDetails") public
 * ResponseEntity<List<Payment>> getAllPaymentDetails() { List<Payment>
 * pl=paymentservice.getAllPaymentDetails(); ResponseEntity<List<Payment>>
 * re=new ResponseEntity<List<Payment>>(pl,HttpStatus.OK); return re; }
 */