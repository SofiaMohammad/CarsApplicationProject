package com.cars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Order;
import com.cars.service.OrderService;

@RestController
@RequestMapping(path="/order")

public class OrderController {

	@Autowired
	OrderService orderservice;
	
	@PostMapping(path="/addorder")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) throws ResourceNotFoundException
	{
       Order e1=orderservice.addOrder(order);
		ResponseEntity<Order> re=new ResponseEntity<Order>(e1,HttpStatus.OK);
		return re;
	}
	@PutMapping(path="/updateOrder/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable long id, Order order) throws Exception
	{
		Order e1=orderservice.updateOrder(id, order);
		ResponseEntity<Order> re=new ResponseEntity<Order>(e1,HttpStatus.OK);
		return re;
	}
	@DeleteMapping(path="/removeorder/{id}")
	public ResponseEntity<String> removeOrder(@PathVariable long id) throws Exception
	{
		orderservice.removeOrder(id);
		ResponseEntity<String> re=new ResponseEntity<String>("Deleted",HttpStatus.OK);
		return re;
	}
	
	@GetMapping(path="/getAllOrders")
	public ResponseEntity<List<Order>> getAllOrders()
	{
		List<Order> le=orderservice.getAllOrders();
		ResponseEntity<List<Order>> re=new ResponseEntity<List<Order>>(le,HttpStatus.OK);
		return re;
	}
	@GetMapping(path="/getOrder/{id}")
	public ResponseEntity<Order> getOrderDetails(@PathVariable long id) throws Exception
	{
		Order e1=orderservice.getOrderDetails(id);
		ResponseEntity<Order> re=new ResponseEntity<Order>(e1,HttpStatus.OK);
		return re;
	}
	
}
