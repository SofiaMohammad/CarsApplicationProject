package com.cars.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;


@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentId;
	@NotBlank
	private String type1;
	@NotBlank
	private String status;
	@NotBlank
	private long amount;
	
	 @OneToMany(cascade = CascadeType.ALL)
	 private List<Card> card;
	 
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	  public List<Card> getCard() { 
		  return card; 
		  }
	  public void setCard(List<Card> card) {
		  this.card =card;
		  }
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", type1=" + type1 + ", status=" + status + ", amount=" + amount
				+ ", card=" + card + "]";
	}
	  
	  
}
