package com.cars.service;


import java.util.Optional;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Customer;
import com.cars.beans.User;

public interface IUserService {
	public Optional<Customer> signIn(long userIdno,String password) throws Exception;
	public User signOut(long userIdno) throws Exception;
	public User createUser(User u) throws ResourceNotFoundException;
	public User changeUserpassword(long userId, String password, String newpassword, String confirm_password) throws Exception;

}
