package com.sbu.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="creditcard")
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long cardId;
	
	private String holder_FirstName;
	private String holder_LastName;
	
	@Enumerated(EnumType.STRING)
	private CardCompany cardCompany;
	
	private String cardNumber;
	
	public String getHolder_FirstName() {
		return holder_FirstName;
	}
	public void setHolder_FirstName(String holder_FirstName) {
		this.holder_FirstName = holder_FirstName;
	}
	public String getHolder_LastName() {
		return holder_LastName;
	}
	public void setHolder_LastName(String holder_LastName) {
		this.holder_LastName = holder_LastName;
	}
	
	@Enumerated(EnumType.STRING)
	public CardCompany getCardCompany() {
		return cardCompany;
	}
	
	@Enumerated(EnumType.STRING)
	public void setCardCompany(CardCompany cardCompany) {
		this.cardCompany = cardCompany;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	private Date expiration;
	private int cvv;
	

}
