package com.sbu.model;

import java.net.URL;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//TESTING COMMENT
@Entity
@Table(name="song")
public class Song {
	
	
	@Id
	@GeneratedValue
	@Column(name="SONG_ID")
	private Long songId;
	
	@Column(name="SONG_NAME")
	private String songName;
	
	@Column(name="SONG_GENRE")
	private String songGenre;
	
	@Column(name="SONG_DATE")
	private String date;
	
	@Column(name="DURATION")
	private String duration;
	
	/* Using ID for storing file, filename is obsolete.
	@Column(name="FILE_NAME")
	private String fileName;
	*/
	
	@Column(name="RATING")
	private double rating;
	
	@Column(name="APPROVED")
	private boolean approved;
	
	@Column(name="LISTEN_COUNT")
	private long listenCount;
	
	@ManyToOne
    @JoinColumn(name="ALBUM_ID", nullable=false)
	private Album album;
	
	@ManyToMany
	@JoinTable(name="JoinRecentlyPlayedListAndSong", joinColumns={@JoinColumn(name="songId")}, inverseJoinColumns={@JoinColumn(name="playlistId")})
	private Collection<RecentlyPlayedList> recentlyPlayedList_ID;
	

	public Collection<RecentlyPlayedList> getRecentlyPlayedList() {
		return recentlyPlayedList_ID;
	}

	public void setRecentlyPlayedList(Collection<RecentlyPlayedList> recentlyPlayedList) {
		this.recentlyPlayedList_ID = recentlyPlayedList;
	}
	
	public Long getSongId(){
		return songId;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}



	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public long getListenCount() {
		return listenCount;
	}

	public void setListenCount(long listenCount) {
		this.listenCount = listenCount;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
	
	
}
