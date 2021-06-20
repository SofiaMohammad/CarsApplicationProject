 package com.cars.service;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

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
import com.cars.beans.Customer;
import com.cars.beans.Order;
import com.cars.dao.IOrderRepository;
@SpringBootTest
class OrderServiceTest {
	
	@Autowired
	OrderService oservice;
	
	@MockBean
	IOrderRepository orepo;

	@Test
	void testAddOrder() throws ResourceNotFoundException {
		Address ad=new Address();
		ad.setAid(1);
		ad.setArea("Srnagar");
		ad.setCity("Vizag");
		ad.setDoorNo("852-545");
		ad.setPincode(533101);
		ad.setState("Andhra Pradesh");
		ad.setStreet("JType Quarters");
		
		Customer c=new Customer();
		c.setUserId(10);
		c.setAddress(ad);
		c.setContactNo("9490806700");
		c.setName("Sofia");
		c.setDob("2019-20-5");
		c.setEmail("sofia123@gmail.com");
		
		Order a = new Order();
		a.setAmount(1400000);
		a.setBillingDate("18-06-2021");
		a.setOrderId(1);
		a.setCustomer(c);
		a.setPaymentMethod("card");
		
		 Mockito.when(orepo.save(a)).thenReturn(a);
		   assertThat(oservice.addOrder(a)).isEqualTo(a);
		
	}

	@Test
	void testRemoveOrder() {
		Address ad=new Address();
		ad.setAid(1);
		ad.setArea("Srnagar");
		ad.setCity("Vizag");
		ad.setDoorNo("852-545");
		ad.setPincode(533101);
		ad.setState("Andhra Pradesh");
		ad.setStreet("Jtype Quarters");
		
		Customer c=new Customer();
		c.setUserId(10);
		c.setAddress(ad);
		c.setContactNo("9490806700");
		c.setName("Sofia");
		c.setDob("2019-20-5");
		c.setEmail("sofia123@gmail.com");
		
		Order a = new Order();
		a.setAmount(1400000);
		a.setBillingDate("18-06-2021");
		a.setOrderId(1);
		a.setCustomer(c);
		a.setPaymentMethod("card");
		
		Optional<Order> a1=Optional.of(a);
		Mockito.when(orepo.findById((long) 1)).thenReturn(a1);
		 Mockito.when(orepo.existsById(a.getOrderId())).thenReturn(false);
		   assertFalse(orepo.existsById(a.getOrderId()));
		
	}

	@Test
	void testUpdateOrder() throws Exception {
		
		Address ad=new Address();
		ad.setAid(1);
		ad.setArea("Srnagar");
		ad.setCity("Vizag");
		ad.setDoorNo("852-545");
		ad.setPincode(533101);
		ad.setState("Andhra Pradesh");
		ad.setStreet("JType Quarters");
		
		Customer c=new Customer();
		c.setUserId(10);
		c.setAddress(ad);
		c.setContactNo("9490806700");
		c.setName("Sofia");
		c.setDob("2019-20-5");
		c.setEmail("sofia@gmail.com");
		
		Order a = new Order();
		a.setAmount(1400000);
		a.setBillingDate("18-06-2021");
		a.setOrderId(1);
		a.setCustomer(c);
		a.setPaymentMethod("cash");
		
		Optional<Order> c1=Optional.of(a);
		Mockito.when(orepo.findById((long) 1)).thenReturn(c1);
		a.setBillingDate("20-06-2021");
   Mockito.when(orepo.save(a)).thenReturn(a);
   assertThat(oservice.updateOrder(a.getOrderId(), a)).isEqualTo(a);

		
	}

	@Test
	void testGetOrderDetails() throws Exception {
		Address ad=new Address();
		ad.setAid(1);
		ad.setArea("Srnagar");
		ad.setCity("Vizag");
		ad.setDoorNo("852-545");
		ad.setPincode(533101);
		ad.setState("Andhra Pradesh");
		ad.setStreet("JType Quarters");
		
		Customer c=new Customer();
		c.setUserId(10);
		c.setAddress(ad);
		c.setContactNo("9490806700");
		c.setName("Sofia");
		c.setDob("2019-20-5");
		c.setEmail("sofia123@gmail.com");
		
		Order a = new Order();
		a.setAmount(1400000);
		a.setBillingDate("18-06-2021");
		a.setOrderId(1);
		a.setCustomer(c);
		a.setPaymentMethod("card");
		
		
		Optional<Order> c2=Optional.of(a);
		Mockito.when(orepo.findById((long) 1)).thenReturn(c2);
	    assertThat(oservice.getOrderDetails(1)).isEqualTo(a);
	}

	@Test
	void testGetAllOrders() {
		
		Address ad=new Address();
		ad.setAid(1);
		ad.setArea("Srnagar");
		ad.setCity("Vizag");
		ad.setDoorNo("852-545");
		ad.setPincode(533101);
		ad.setState("Andhra Pradesh");
		ad.setStreet("JType Quarters");
		
		Customer c=new Customer();
		c.setUserId(10);
		c.setAddress(ad);
		c.setContactNo("9490806700");
		c.setName("Sofia");
		c.setDob("2019-20-5");
		c.setEmail("sofia123@gmail.com");
		
		Order a = new Order();
		a.setAmount(1400000);
		a.setBillingDate("18-06-2021");
		a.setOrderId(1);
		a.setCustomer(c);
		
		Order a1 = new Order();
		a1.setAmount(2400000);
		a1.setBillingDate("18-06-2022");
		a1.setOrderId(2);
		a1.setCustomer(c);
		a.setPaymentMethod("cash");
		
		List<Order> orderList = new ArrayList<>();
		orderList.add(a);
		orderList.add(a1);
		Mockito.when(orepo.findAll()).thenReturn(orderList);
		assertThat(oservice.getAllOrders()).isEqualTo(orderList);
	}

}

