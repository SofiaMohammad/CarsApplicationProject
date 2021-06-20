package com.cars.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Car;
import com.cars.dao.ICarRepository;


@Service
public class CarService implements ICarService  {
	
	@Autowired
	ICarRepository repo;
	
	@Override
	public Car addCar(Car car) throws ResourceNotFoundException {
		long id=car.getCarId();
		Optional<Car> s=repo.findById(id);
		if(s.isPresent()) {
		throw new ResourceNotFoundException(id +" is already present in the database");
		}
		else {
		repo.save(car);
		}
	  return car;
	}

	public String removeCarbyId(long id) throws ResourceNotFoundException {
		Optional<Car> a1=repo.findById(id);
		if(a1.isPresent()) {
		repo.deleteById(id);
		return "Deleted";
		}
		else 
		{
			throw new ResourceNotFoundException("Car with id" + id +" is not present in the database");
		}
	}

	@Override
	public Car updateCar(long id, Car car) throws Exception {
		Supplier<Exception> s = () -> new ResourceNotFoundException("Car Id is not present in the database");
		Car c1=repo.findById(id).orElseThrow(s);
		c1.setBrand(car.getBrand());
			c1.setModel(car.getModel());
			c1.setRegistrationState(car.getRegistrationState());
			c1.setRegistrationYear(car.getRegistrationYear());
			c1.setVariant(car.getVariant());
			c1.setPrice(car.getPrice());
			repo.save(c1);
			return c1;
	}

	@Override
	public Car getCar(long id)throws Exception {
		Supplier<Exception> s = () -> new ResourceNotFoundException("Car with id" + id +" is not present in the database");
		Car c1=repo.findById(id).orElseThrow(s);
		return c1;
	}

	@Override
	public List<Car> getAllCars() {
		List<Car> le = repo.findAll();
		return le;
	}
	
	@Override
	public List<Car> getCarsByModel(String model) {
		List<Car> Mo = repo.getCarsByModel(model);
		return Mo;
	}

	@Override
	public List<Car> getCarsByBrand(String brand) {
		List<Car> Brand = repo.getCarsByBrand(brand);
		return Brand;
	}
	@Override
	public List<Car> getCarsByPrice(long price) {
		List<Car> Price = repo.getCarsByPrice(price);
		return Price;
	}


	

}
