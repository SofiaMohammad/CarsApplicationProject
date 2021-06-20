package com.cars.service;

import java.util.List;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Order;


public interface IOrderService {
	public Order addOrder(Order order) throws ResourceNotFoundException;
	public String removeOrder(long id) throws Exception;
	public Order  updateOrder(long id, Order order) throws Exception;
	public Order  getOrderDetails(long id) throws Exception;
	public List<Order> getAllOrders(); 

}
