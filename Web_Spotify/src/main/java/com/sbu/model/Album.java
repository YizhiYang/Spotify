package com.sbu.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="album")
public class Album {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="ALBUM_ID")
	private Long albumId;
	
	@Column(name="ALBUM_NAME")
	private String album_name;
	
	@Column(name="ALBUM_TOTALCOUNT")
	private int numOfSongs;
	
	@Column(name="ALBUM_TOTALDURATION")
	private double totalDuration;

	
	@OneToMany
	private List<Song> songs;
	
	@ManyToMany(mappedBy="album")
	private List<ArtistUser> artistUsers;
	
	
	public double getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(double totalDuration) {
		this.totalDuration = totalDuration;
	}

	public List<ArtistUser> getArtists() {
		return artistUsers;
	}

	public void setArtists(List<ArtistUser> artistUsers) {
		this.artistUsers = artistUsers;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	public int getNumOfSongs() {
		return numOfSongs;
	}

	public void setNumOfSongs(int numOfSongs) {
		this.numOfSongs = numOfSongs;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
}
