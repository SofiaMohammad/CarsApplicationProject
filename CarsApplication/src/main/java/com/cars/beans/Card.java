package com.cars.beans;


import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Card {
	@NotBlank
	private String cardName;
	@Id
	@NotBlank
	private String cardNumber;
	@NotBlank
    private String cardExpiry;
	@NotBlank
    private String cvv;
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardExpiry() {
		return cardExpiry;
	}
	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	@Override
	public String toString() {
		return "Card [cardName=" + cardName + ", cardNumber=" + cardNumber + ", cardExpiry=" + cardExpiry + ", cvv="
				+ cvv + "]";
	}
    
}
