package com.sbu.model;

import java.net.URL;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="advertisement")
public class Advertisement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AD_ID")
	private Long id;
	
	@Column(name="IMAGEURL")
	private String imageUrl;
	
	@Column(name="ADSURL")
	private String adsUrl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAdsUrl() {
		return adsUrl;
	}
	public void setAdsUrl(String adsUrl) {
		this.adsUrl = adsUrl;
	}
}
