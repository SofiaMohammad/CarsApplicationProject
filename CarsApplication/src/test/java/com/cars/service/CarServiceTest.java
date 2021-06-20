package com.cars.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Car;
import com.cars.dao.ICarRepository;

@SpringBootTest
class CarServiceTest {
	
	@Autowired
	CarService carservice;
	
	@MockBean
	ICarRepository carrepo;

	@Test
	void testAddCar() throws ResourceNotFoundException {
			Car c1=new Car();
			c1.setBrand("Hyundai");
			c1.setCarId(1);
			c1.setModel("i20");
			c1.setPrice(1400000);
			c1.setRegistrationState("kerala");
			c1.setRegistrationYear("2020");
			c1.setVariant("sportz");
	   Mockito.when(carrepo.save(c1)).thenReturn(c1);
	   assertThat(carservice.addCar(c1)).isEqualTo(c1);
		}
	@Test
	void testRemoveCarbyId() {
		Car c1=new Car();
		c1.setBrand("Hyundai");
		c1.setCarId(1);
		c1.setModel("i20");
		c1.setPrice(1400000);
		c1.setRegistrationState("kerala");
		c1.setRegistrationYear("2020");
		c1.setVariant("sportz");
		Optional<Car> c2=Optional.of(c1);
		Mockito.when(carrepo.findById((long) 1)).thenReturn(c2);
		 Mockito.when(carrepo.existsById(c1.getCarId())).thenReturn(false);
		   assertFalse(carrepo.existsById(c1.getCarId()));
	}

	@Test
	void testUpdateCar() throws Exception{
		Car c1=new Car();
		c1.setBrand("Hyundai");
		c1.setCarId(1);
		c1.setModel("i20");
		c1.setPrice(1400000);
		c1.setRegistrationState("kerala");
		c1.setRegistrationYear("2020");
		c1.setVariant("sportz");
		Optional<Car> c2=Optional.of(c1);
		Mockito.when(carrepo.findById((long) 1)).thenReturn(c2);
		c1.setBrand("Maruti");
   Mockito.when(carrepo.save(c1)).thenReturn(c1);
   assertThat(carservice.updateCar(c1.getCarId(), c1)).isEqualTo(c1);
	}

	@Test
	void testGetCar() throws Exception{
		Car c1=new Car();
		c1.setBrand("Hyundai");
		c1.setCarId(1);
		c1.setModel("i20");
		c1.setPrice(1400000);
		c1.setRegistrationState("kerala");
		c1.setRegistrationYear("2020");
		c1.setVariant("sportz");
		Optional<Car> c2=Optional.of(c1);
		Mockito.when(carrepo.findById((long) 1)).thenReturn(c2);
	    assertThat(carservice.getCar(1)).isEqualTo(c1);
	}

	@Test
	void testGetAllCars() {
		Car c1=new Car();
		c1.setBrand("Hyundai");
		c1.setCarId(1);
		c1.setModel("i20");
		c1.setPrice(1400000);
		c1.setRegistrationState("kerala");
		c1.setRegistrationYear("2020");
		c1.setVariant("sportz");
		
		Car c2 = new Car();
		c2.setBrand("Kia");
		c2.setCarId(2);
		c2.setModel("seltos");
		c2.setPrice(1700000);
		c2.setRegistrationState("Telengana");
		c2.setBrand("2020");
		c2.setVariant("Ev");
		List<Car> carList = new ArrayList<>();
		carList.add(c1);
		carList.add(c2);
		
		Mockito.when(carrepo.findAll()).thenReturn(carList);
		assertThat(carservice.getAllCars()).isEqualTo(carList);
	}

	@Test
	void testGetCarsByModel() {
		List<Car> c=new ArrayList<>();
		Car c1 = new Car();
		c1.setBrand("Hyundai");
		c1.setCarId(1);
		c1.setModel("i20");
		c1.setPrice(1400000);
		c1.setRegistrationState("kerala");
		c1.setRegistrationYear("2020");
		c1.setVariant("sportz");
		
		Car c2 = new Car();
		c1.setBrand("Hyundai");
		c1.setCarId(2);
		c1.setModel("i20");
		c1.setPrice(1400000);
		c1.setRegistrationState("kerala");
		c1.setRegistrationYear("2020");
		c1.setVariant("sportz");
		
		c.add(c1);
		c.add(c2);
		
		 Mockito.when(carrepo.getCarsByModel(c1.getModel())).thenReturn(c);
		    
		    assertThat(carservice.getCarsByModel(c1.getModel())).isEqualTo(c);
		 
	}
	@Test
	void testGetCarsByBrand() {
		List<Car> c=new ArrayList<>();
		Car c1 = new Car();
		c1.setBrand("Hyundai");
		c1.setCarId(1);
		c1.setModel("i20");
		c1.setPrice(1400000);
		c1.setRegistrationState("kerala");
		c1.setRegistrationYear("2020");
		c1.setVariant("sportz");
		
		Car c2 = new Car();
		c1.setBrand("Hyundai");
		c1.setCarId(2);
		c1.setModel("i20");
		c1.setPrice(1400000);
		c1.setRegistrationState("kerala");
		c1.setRegistrationYear("2020");
		c1.setVariant("sportz");
		
		c.add(c1);
		c.add(c2);
		
		 Mockito.when(carrepo.getCarsByBrand(c1.getBrand())).thenReturn(c);
		    
		    assertThat(carservice.getCarsByBrand(c1.getBrand())).isEqualTo(c);
	}

	@Test
	void testGetCarsByPrice() {
		List<Car> c=new ArrayList<>();
		Car c1 = new Car();
		c1.setBrand("Hyundai");
		c1.setCarId(1);
		c1.setModel("i20");
		c1.setPrice(1400000);
		c1.setRegistrationState("kerala");
		c1.setRegistrationYear("2020");
		c1.setVariant("sportz");
		
		Car c2 = new Car();
		c1.setBrand("Hyundai");
		c1.setCarId(2);
		c1.setModel("i20");
		c1.setPrice(1400000);
		c1.setRegistrationState("kerala");
		c1.setRegistrationYear("2020");
		c1.setVariant("sportz");
		
		c.add(c1);
		c.add(c2);
		
		 Mockito.when(carrepo.getCarsByPrice(c1.getPrice())).thenReturn(c);
		    
		    assertThat(carservice.getCarsByPrice(c1.getPrice())).isEqualTo(c);
	}

}
