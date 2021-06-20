package com.cars.service;

import java.util.List;
import java.util.Optional;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Appointment;


public interface IAppointmentService {

	public Appointment addAppointment(Appointment appointment) throws ResourceNotFoundException;
	public String removeAppointmentbyId(long id) throws Exception; 
	public Appointment updateAppointment(long id, Appointment appointment) throws Exception;
	public Optional<Appointment> getAppointment(long id) throws Exception;
	public List<Appointment> getAllAppointments(); 
	public List<Appointment> getOpenAppointments();
}
