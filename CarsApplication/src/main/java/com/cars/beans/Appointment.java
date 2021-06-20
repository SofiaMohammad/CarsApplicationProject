package com.cars.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long appointmentId;
	@NotBlank
	private String location;
	@NotBlank
	private String inspectionType;
	@NotBlank
	private String preferredDate;
	@NotBlank
	private String preferredTime;
	@ManyToOne(cascade = CascadeType.ALL)
	@NotBlank
	private Customer customer;
	@OneToOne(cascade = CascadeType.ALL)
	@NotBlank
	private Payment payment;
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInspectionType() {
		return inspectionType;
	}
	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}
	public String getPreferredDate() {
		return preferredDate;
	}
	public void setPreferredDate(String preferredDate) {
		this.preferredDate = preferredDate;
	}
	public String getPreferredTime() {
		return preferredTime;
	}
	public void setPreferredTime(String preferredTime) {
		this.preferredTime = preferredTime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", location=" + location + ", inspectionType="
				+ inspectionType + ", preferredDate=" + preferredDate + ", preferredTime=" + preferredTime
				+ ", customer=" + customer + ", payment=" + payment + "]";
	}

	
}
