package com.cars.service;

import java.util.List;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Car;



public interface ICarService {
	public Car addCar(Car car) throws ResourceNotFoundException;
	public String removeCarbyId(long id) throws ResourceNotFoundException;
	public Car updateCar(long id, Car car) throws Exception;
	public Car getCar(long id) throws Exception;
	public List<Car> getAllCars();
	public List<Car> getCarsByModel(String model);
	public List<Car> getCarsByBrand(String brand);
	public List<Car> getCarsByPrice(long price);

}
