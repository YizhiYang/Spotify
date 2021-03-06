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
	private String albumName;
	
	@Column(name="ALBUM_IMAGE_URL")
	private String albumImageUrl;
	
	@Column(name="ALBUM_TOTALCOUNT")
	private int numOfSongs;
	
	@Column(name="ALBUM_TOTALDURATION")
	private double totalDuration;

	
	@OneToMany(mappedBy="album")
	private List<Song> songs;
	
	@ManyToMany(mappedBy="album" )
	private List<ArtistUser> artistUsers = new ArrayList<ArtistUser>();
	
	
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

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbum_name(String albumName) {
		this.albumName = albumName;
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

	public String getAlbumImageUrl() {
		return albumImageUrl;
	}

	public void setAlbumImageUrl(String albumImageUrl) {
		this.albumImageUrl = albumImageUrl;
	}
	
}
