package com.sbu.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="basicUser")
public class BasicUser extends User{
	
	private int songsRemainingBeforeAd;
	private int songsAvailableToday;
	
	public int getSongsRemainingBeforeAd() {
		return songsRemainingBeforeAd;
	}
	public void setSongsRemainingBeforeAd(int songsRemainingBeforeAd) {
		this.songsRemainingBeforeAd = songsRemainingBeforeAd;
	}
	public int getSongsAvailableToday() {
		return songsAvailableToday;
	}
	public void setSongsAvailableToday(int songsAvailableToday) {
		this.songsAvailableToday = songsAvailableToday;
	}

}
