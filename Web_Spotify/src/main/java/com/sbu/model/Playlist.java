package com.sbu.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="playlist")
public class Playlist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="PLAYLIST_ID")
	private Long id;
	
	@OneToOne
	private User owner;
	
	private double numOfSongs;
	private double numOfFollwers;
	
	private int privacy;
	
	@Column(name="PLAYLIST_NAME")
	private String name;
	
	private Date created;
	
	//private List<Genre> genre;

	@OneToMany
	private List<Song> songs;
	
	@ManyToMany
    @JoinTable(
        name = "Followers", 
        		joinColumns = { @JoinColumn(name = "PLAYLIST_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "USER_ID") }
    )
	private List<User> followers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public double getNumOfSongs() {
		return numOfSongs;
	}

	public void setNumOfSongs(double numOfSongs) {
		this.numOfSongs = numOfSongs;
	}

	public double getNumOfFollwers() {
		return numOfFollwers;
	}

	public void setNumOfFollwers(double numOfFollwers) {
		this.numOfFollwers = numOfFollwers;
	}

	public int getPrivacy() {
		return privacy;
	}

	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	

}
