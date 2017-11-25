package com.sbu.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	
	@Column(name="USER_ID")
	private Long id;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="CREATEDDATE")
	private Date createDate;
	
	@Column(name="LOGGEDIN")
	private boolean loggedin;
	
	@Column(name="USERTYPE")
	private UserType userType;

	@ManyToMany
    @JoinTable(
        name = "user_song", 
        		joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, 
        inverseJoinColumns = { @JoinColumn(name = "SONG_ID", nullable = false, updatable = false) }
    )
	private List<Song> followedSongs = new ArrayList<Song>();
	
	@ManyToMany
    @JoinTable(
        name = "user_album", 
        		joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, 
        inverseJoinColumns = { @JoinColumn(name = "ALBUM_ID", nullable = false, updatable = false) }
    )
	private List<Album> followedAlbums = new ArrayList<Album>();
	
	@ManyToMany
    @JoinTable(
        name = "user_artist", 
        		joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, 
        inverseJoinColumns = { @JoinColumn(name = "ARTIST_ID", nullable = false, updatable = false) }
    )
	private List<ArtistUser> followedArtists = new ArrayList<ArtistUser>();

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Song> getFollowedSongs() {
		return followedSongs;
	}
	public void setFollowedSongs(List<Song> followedSongs) {
		this.followedSongs = followedSongs;
	}
	public List<Album> getFollowedAlbums() {
		return followedAlbums;
	}
	public void setFollowedAlbums(List<Album> followedAlbums) {
		this.followedAlbums = followedAlbums;
	}
	public List<ArtistUser> getFollowedArtists() {
		return followedArtists;
	}
	public void setFollowedArtists(List<ArtistUser> followedArtists) {
		this.followedArtists = followedArtists;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
}
