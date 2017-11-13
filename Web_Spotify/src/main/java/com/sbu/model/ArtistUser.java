package com.sbu.model;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="artist")
public class ArtistUser implements Serializable{

	
	/* Song doesn't need artist because a song must belong to an album and an album must belong to an artist. That's enough.
	@ManyToMany
    @JoinTable(
        name = "Artist_Songs", 
        joinColumns = { @JoinColumn(name = "ARTIST_ID", referencedColumnName = "USER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "songId") }
    )
	private List<Song> songs;
	*/
	
	@Id
	@GeneratedValue
	@Column(name="ARTIST_ID")
	private long artistID;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
	private User user;
	
	@Column(name="ARTIST_NAME")
	private String artistName;
	
	
	@ManyToMany(cascade = { 
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })
    @JoinTable(
        name = "Artist_Albums", 
        		joinColumns = { @JoinColumn(name = "ARTIST_ID", nullable = false, updatable = false) }, 
        inverseJoinColumns = { @JoinColumn(name = "ALBUM_ID", nullable = false, updatable = false) }
    )
	private List<Album> album = new ArrayList<Album>();

	
	


	@ManyToMany
    @JoinTable(
        name = "Followers", 
        		joinColumns = { @JoinColumn(name = "ARTIST_ID", referencedColumnName = "USER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "USER_ID") }
    )
	private List<User> followers;

	
	public List<Album> getAlbum() {
		return album;
	}


	public void setAlbum(List<Album> album) {
		this.album = album;
	}


	public List<User> getFollowers() {
		return followers;
	}


	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}


	public long getArtistID() {
		return artistID;
	}


	public void setArtistID(long artistID) {
		this.artistID = artistID;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getArtistName() {
		return artistName;
	}


	public void setArtistName(String artistName) {
		artistName = artistName;
	}
}
