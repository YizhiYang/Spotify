package com.sbu.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="billinginfo")
public class BillingInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="BILLING_ID")
	private Long id;
	
	@OneToOne
	private User user;
	
	@OneToMany
	private List<AddressInfo> address;
	
	@OneToMany
	private List<CreditCard> cards;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<AddressInfo> getAddress() {
		return address;
	}

	public void setAddress(List<AddressInfo> address) {
		this.address = address;
	}

	public List<CreditCard> getCards() {
		return cards;
	}

	public void setCards(List<CreditCard> cards) {
		this.cards = cards;
	}
}
