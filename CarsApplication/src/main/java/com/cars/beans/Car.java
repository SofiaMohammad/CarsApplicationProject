package com.cars.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
@Entity 
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long carId;
	@NotBlank
	private String brand;
	@NotBlank
	private String model;
	@NotBlank
	private String variant;
	@NotBlank
	private long price;
	@NotBlank
	private String registrationYear;
	@NotBlank
	private String registrationState;
	public long getCarId() {
		return carId;
	}
	public void setCarId(long carId) {
		this.carId = carId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVariant() {
		return variant;
	}
	public void setVariant(String variant) {
		this.variant = variant;
		
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getRegistrationYear() {
		return registrationYear;
	}
	public void setRegistrationYear(String registrationYear) {
		this.registrationYear = registrationYear;
	}
	public String getRegistrationState() {
		return registrationState;
	}
	public void setRegistrationState(String registrationState) {
		this.registrationState = registrationState;
	}
	@Override
	public String toString() {
		return "Car [carId=" + carId + ", brand=" + brand + ", model=" + model + ", variant=" + variant + ", price="
				+ price + ", registrationYear=" + registrationYear + ", registrationState=" + registrationState + "]";
	}
	
	
	
		
	

}
