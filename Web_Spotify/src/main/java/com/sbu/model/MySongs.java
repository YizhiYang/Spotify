package com.sbu.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="mySongs")
public class MySongs {
	
	@Id
	@GeneratedValue
	private Long mySongsId;
	
	@OneToOne
	private User owner;
	
	@OneToMany
	private List<Song> songs;

	public Long getMySongsId() {
		return mySongsId;
	}

	public void setMySongsId(Long mySongsId) {
		this.mySongsId = mySongsId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
}
