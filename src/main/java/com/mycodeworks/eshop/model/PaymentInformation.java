package com.mycodeworks.eshop.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class PaymentInformation {
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private long id;

	@Column(name="cardholder_name")
	private String cardHolderName;

	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expiration_date")
	private String expirationDate;
	
	@Column(name="cvv")
	private String cvv;
	
}
