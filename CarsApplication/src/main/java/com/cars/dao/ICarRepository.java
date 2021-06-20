package com.cars.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cars.beans.Car;
@Repository
public interface ICarRepository extends JpaRepository<Car,Long>  {
	
	@Query("Select c from Car c where c.carId=?1")
	Car updateCar(Long id);
	@Query("Select c from Car c where c.model=?1")
	List<Car> getCarsByModel(String model1);
	@Query("Select c from Car c where c.brand=?1")
	List<Car> getCarsByBrand(String brand1);
	@Query("Select c from Car c where c.price=?1")
	List<Car> getCarsByPrice(long price1);


	
	/*
	 * public Car addCar(Car car); public Car removeCar(long id); public Car
	 * updateCar(long id, Car car); public Car getCar(long id); public List<Car>
	 * getAllCars(); public List<Car> getCarsByLocation(); public List<Car>
	 * getCarsByModel(); public List<Car> getCarsByBrand();
	 */
}
