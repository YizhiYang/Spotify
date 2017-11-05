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
	
	private Date created;
	private int showCount;
	private URL source;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getShowCount() {
		return showCount;
	}
	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}
	public URL getSource() {
		return source;
	}
	public void setSource(URL source) {
		this.source = source;
	}
	public int getDuraingSecs() {
		return duraingSecs;
	}
	public void setDuraingSecs(int duraingSecs) {
		this.duraingSecs = duraingSecs;
	}
	private int duraingSecs;
	
}
