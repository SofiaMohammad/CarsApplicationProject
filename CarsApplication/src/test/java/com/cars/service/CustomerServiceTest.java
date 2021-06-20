package com.cars.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Address;
import com.cars.beans.Customer;
import com.cars.dao.ICustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CustomerServiceTest {
	@Autowired
	CustomerService cuservice;
	
	@MockBean
	ICustomerRepository repo;

	@Test
	void testgetAllCustomers() {
		List<Customer> c=new ArrayList<>(); 
		Address a=new Address();
		a.setAid(1);
		a.setArea("Mallayapeta");
		a.setCity("Rajahmundry");
		a.setDoorNo("852-545");
		a.setPincode(533101);
		a.setState("Andhra Pradesh");
		a.setStreet("Industrial Colony");
		
		Customer c1=new Customer();
		c1.setUserId(10);
		c1.setAddress(a);
		c1.setContactNo("9490806700");
		c1.setName("Sunil");
		c1.setDob("2019-20-5");
		c1.setEmail("yaghnasunil@gmail.com");
		
		Customer c2=new Customer();
		c2.setUserId(12);
		c2.setAddress(a);
		c2.setContactNo("949080670");
		c2.setName("Sunil");
		c2.setDob("2019-20-5");
		c2.setEmail("yaghnasunil@gmail.com");
		
		c.add(c1);
		c.add(c2);
		
		Mockito.when(repo.findAll()).thenReturn(c);
		assertThat(cuservice.getAllCustomers()).isEqualTo(c);
		
	}
	
	@Test
	void testAddCustomer() throws ResourceNotFoundException {
		Address a=new Address();
		a.setAid(1);
		a.setArea("Mallayapeta");
		a.setCity("Rajahmundry");
		a.setDoorNo("852-545");
		a.setPincode(533101);
		a.setState("Andhra Pradesh");
		a.setStreet("Industrial Colony");
		
		Customer c1 = new Customer();
        c1.setUserId(10);
        c1.setName("Ravich");
        c1.setEmail("ravi@gmail.com");
        c1.setAddress(a);
        c1.setContactNo("9490806700");
        c1.setDob("05-02-1995");
       
        Mockito.when(repo.save(c1)).thenReturn(c1);
        assertThat(cuservice.addCustomer(c1)).isEqualTo(c1);
	}

	@Test
	void testgetCustomer() throws Exception {
		Address a=new Address();
		a.setAid(1);
		a.setArea("Mallayapeta");
		a.setCity("Rajahmundry");
		a.setDoorNo("852-545");
		a.setPincode(533101);
		a.setState("Andhra Pradesh");
		a.setStreet("Industrial Colony");
		
		Customer c1 = new Customer();
        c1.setUserId(10);
        c1.setName("Ravich");
        c1.setEmail("ravi@gmail.com");
        c1.setAddress(a);
        c1.setContactNo("9490806700");
        c1.setDob("05-02-1995");
        
        Optional<Customer> c2=Optional.of(c1);
		   Mockito.when(repo.findById((long) 10)).thenReturn(c2);

		   assertThat(cuservice.getCustomer(c1.getUserId())).isEqualTo(c2);
	}
	
	@Test
	void testRemoveCustomer() {
		Address a=new Address();
		a.setAid(1);
		a.setArea("Mallayapeta");
		a.setCity("Rajahmundry");
		a.setDoorNo("852-545");
		a.setPincode(533101);
		a.setState("Andhra Pradesh");
		a.setStreet("Industrial Colony");
		
		Customer c1 = new Customer();
        c1.setUserId(10);
        c1.setName("Ravich");
        c1.setEmail("ravi@gmail.com");
        c1.setAddress(a);
        c1.setContactNo("9490806700");
        c1.setDob("05-02-1995");
        
        Optional<Customer> c=Optional.of(c1);
		Mockito.when(repo.findById((long) 10)).thenReturn(c);
		 Mockito.when(repo.existsById(c1.getUserId())).thenReturn(false);
		   assertFalse(repo.existsById(c1.getUserId()));
	}
	
	@Test
	void testupdateCustomer() throws Exception {
		Address a=new Address();
		a.setAid(1);
		a.setArea("Mallayapeta");
		a.setCity("Rajahmundry");
		a.setDoorNo("852-545");
		a.setPincode(533101);
		a.setState("Andhra Pradesh");
		a.setStreet("Industrial Colony");
		
		Customer c1 = new Customer();
        c1.setUserId(10);
        c1.setName("Ravich");
        c1.setEmail("ravi@gmail.com");
        c1.setAddress(a);
        c1.setContactNo("9490806700");
        c1.setDob("05-02-1995");
        
        Optional<Customer> c=Optional.of(c1);
        Mockito.when(repo.findById((long) 10)).thenReturn(c);
		c1.setContactNo("7490806700");
		Mockito.when(repo.save(c1)).thenReturn(c1);
		assertThat(cuservice.updateCustomer(c1)).isEqualTo(c1);
	}
	@Test 
	void testgetCustomersByLocation(){
List<Customer> c=new ArrayList<>(); 
		Address a=new Address();
		a.setAid(1);
		a.setArea("Mallayapeta");
		a.setCity("Rajahmundry");
		a.setDoorNo("852-545");
		a.setPincode(533101);
		a.setState("Andhra Pradesh");
		a.setStreet("Industrial Colony");
		Customer c1=new Customer();
		c1.setUserId(10);
		c1.setAddress(a);
		c1.setContactNo("9490806700");
		c1.setName("Sunil");
		c1.setDob("2019-20-5");
		c1.setEmail("yaghnasunil@gmail.com");
		
		Customer c2=new Customer();
		c2.setUserId(12);
		c2.setAddress(a);
		c2.setContactNo("9490806700");
		c2.setName("Sunil");
		c2.setDob("2019-20-5");
		c2.setEmail("yaghnasunil@gmail.com");
		
		c.add(c1);
		c.add(c2);
		
		Mockito.when(repo.findByCitySorted("Rajahmundry")).thenReturn(c);
	    
	    assertThat(cuservice.getCustomersByLocation("Rajahmundry")).isEqualTo(c);
	
		
	}
	
	

}
