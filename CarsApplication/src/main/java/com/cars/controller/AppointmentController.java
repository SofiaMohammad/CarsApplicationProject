package com.cars.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Appointment;
import com.cars.service.AppointmentService;

@RestController
@RequestMapping(path="/appointment")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentservice;
	
	@PostMapping(path="/addappointment")
	public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) throws ResourceNotFoundException
	{
		Appointment a1=appointmentservice.addAppointment(appointment);
		ResponseEntity<Appointment> re=new ResponseEntity<Appointment>(a1,HttpStatus.OK);
		return re;
	}
	@DeleteMapping(path="/removeAppointment/{id}")
	public ResponseEntity<String> removeAppointmentbyId(@PathVariable long id) throws Exception
	{
		appointmentservice.removeAppointmentbyId(id);
		ResponseEntity<String> re=new ResponseEntity<String>("Deleted",HttpStatus.OK);
		return re;
	}
	
	@PutMapping(path="/updateAppointment/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable long id, Appointment appointment) throws Exception
	{
		Appointment a1=appointmentservice.updateAppointment(id, appointment);
		ResponseEntity<Appointment> re=new ResponseEntity<Appointment>(a1,HttpStatus.OK);
		return re;
	}
	@GetMapping(path="/getAllAppointments")
	public ResponseEntity<List<Appointment>>  getAllAppointments()
	{
		List<Appointment> le=appointmentservice.getAllAppointments();
		
		ResponseEntity<List<Appointment>> re=new ResponseEntity<List<Appointment>>(le,HttpStatus.OK);
		return re;
	}
		@GetMapping(path="/getAppointment/{id}")
	public ResponseEntity<Optional<Appointment>> getAppointment(@PathVariable long id) throws Exception
	{
		Optional<Appointment> a1=appointmentservice.getAppointment(id);
		ResponseEntity<Optional<Appointment>> re=new ResponseEntity<Optional<Appointment>>(a1,HttpStatus.OK);
		return re;
	}
	
	
	
	

}
