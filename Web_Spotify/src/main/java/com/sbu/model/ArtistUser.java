package com.sbu.model;

import java.net.URL;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="artist")
public class ArtistUser extends User{

	
	/* Song doesn't need artist because a song must belong to an album and an album must belong to an artist. That's enough.
	@ManyToMany
    @JoinTable(
        name = "Artist_Songs", 
        joinColumns = { @JoinColumn(name = "ARTIST_ID", referencedColumnName = "USER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "songId") }
    )
	private List<Song> songs;
	*/
	
	
	@ManyToMany
    @JoinTable(
        name = "Artist_Albums", 
        		joinColumns = { @JoinColumn(name = "ARTIST_ID", referencedColumnName = "USER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "ALBUM_ID") }
    )
	private List<Album> album;


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


	@ManyToMany
    @JoinTable(
        name = "Followers", 
        		joinColumns = { @JoinColumn(name = "ARTIST_ID", referencedColumnName = "USER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "USER_ID") }
    )
	private List<User> followers;

	
	
}
