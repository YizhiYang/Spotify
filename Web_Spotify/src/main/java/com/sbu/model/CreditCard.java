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
	
	@Column(name="USER")
	private User user;
	
	@Column(name="HOLDER_NAME")
	private String holderName;
	
	@Column(name="CARD_COMPANY")
	@Enumerated(EnumType.STRING)
	private CardCompany cardCompany;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="CARD_NUMBER")
	private String cardNumber;
	
	@Column(name="EXPIRATION")
	private String expiration;
	
	@Column(name="CVV")
	private int cvv;
	
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

	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	

}
