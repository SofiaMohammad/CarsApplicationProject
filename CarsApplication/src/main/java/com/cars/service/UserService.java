package com.cars.service;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cars.advices.ResourceNotFoundException;
import com.cars.beans.Customer;
import com.cars.beans.User;
import com.cars.dao.IUserRepository;

@Service
public class UserService implements IUserService {
	@Autowired
	IUserRepository userrepository;
	String regex = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%&])"
            + "(?=\\S+$).{8,20}$";
	   Pattern p = Pattern.compile(regex);
	@Override
	public Optional<Customer> signIn(long userId,String password)throws Exception 
	{
	   Supplier<Exception> s1 = ()->new ResourceNotFoundException("Register or invalid id and Password.. ");
	   Supplier<Exception> s2 = ()->new ResourceNotFoundException("There are no customer details please fill the deatils in customer using this user id.");
	   Optional<Customer> s11;
	   userrepository.SignIn(userId,password).orElseThrow(s1);
	   s11=Optional.of(userrepository.detailscustomer(userId).orElseThrow(s2));
	   return s11;
	}
	
	@Override
	public User changeUserpassword(long userId,String password,String newpassword,String confirm_password) throws Exception  
	{	
		    String np=newpassword;
		    
		    Supplier<Exception> s1 = ()->new ResourceNotFoundException("User id or password is not correct");
		    User u1=userrepository.Changepassword(userId,password).orElseThrow(s1);
		    if(password.isEmpty() || np.isEmpty() || confirm_password.isEmpty()) {
    			throw new ResourceNotFoundException("Null values are not accepted");
		    }
		    	if(!np.equals(password))  {
		    	if(p.matcher(np).matches() && p.matcher(confirm_password).matches())  {
		    		if(np.equals(confirm_password)) {
		    		u1.setPassword(np);
					userrepository.save(u1);
		    		}
		    		else {
		    			throw new ResourceNotFoundException("new password and confirm password are not same");
		    		}
		    }
		    else {
				  throw new ResourceNotFoundException("Password dosen't match the criteria..it should have alteast one upper,lower,number,special characters like @#$%& and min size of 8 and max of 20"); 

		    }
		    }
			   else {
			    	throw new ResourceNotFoundException("New password shouldn't be same as old password..");

			   }
			return u1;	
	}
	
	@Override
	public User signOut(long userId) throws Exception 
	{
	   Supplier<Exception> s1 = ()->new ResourceNotFoundException("Invalid id for SignOut");
	   User u1=((Optional<User>) userrepository.SignOut(userId)).orElseThrow(s1);
	   return u1;
	}
	
	@Override
	public User createUser(User u) throws ResourceNotFoundException {
		long id=u.getUserId();
		String pas=u.getPassword();
		Optional<User> s=userrepository.findById(id);
		if(s.isPresent()) {
			throw new ResourceNotFoundException(id +" is already present in the database");
		}
		else if(p.matcher(pas).matches()) {
			userrepository.save(u);
		}
		else {
		  throw new ResourceNotFoundException("Password dosen't match the criteria..it should have alteast one upper,lower,number,special characters like @#$%& and min size of 8 and max of 20"); 
		}
		return u;
	}

}
