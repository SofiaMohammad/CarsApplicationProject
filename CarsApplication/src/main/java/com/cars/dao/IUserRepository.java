package com.cars.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cars.beans.Customer;
import com.cars.beans.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	
	@Query("Select u from User u where u.userId=?1 and u.password=?2")
	Optional<User> SignIn(long userId,String password);
	
	@Query("Select u from User u where u.userId=?1")
	Optional<User> SignOut(long userIdno);
	
	@Query("Select u from User u where u.userId=?1 and u.password=?2")
	Optional<User> Changepassword(long userId,String password);
	
	@Query("Select c from Customer c where c.userId=?1")
	Optional<Customer> detailscustomer(long userId);
	
	
	
	/*public User signIn(User user);
	public User signOut(User user);
	public User changePassword(long id, User user);*/

}
