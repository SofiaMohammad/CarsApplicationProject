package com.cars.dao;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cars.beans.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment,Long> {
	
	@Query("select p from Payment p where p.paymentId=?1")
	Optional<Payment> getId(Long id);
	
	 @Query("Select e from Payment e order by e.paymentId") 
	 List<Payment> findByPaymentIdSorted();
	
	/*
	 * public Payment addPayment(Payment payment); public Payment removePayment(long
	 * id); public Payment updatePayment(long id, Payment payment); public Payment
	 * getPaymentDetails(long id); public List<Payment> getAllPaymentDetails();
	 */
}
