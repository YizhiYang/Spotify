package com.sbu.model;

import java.net.URL;
import java.util.Collection;

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
	private Long songId;
	
	private String song_name;
	
	private String duration;
	
	private String fileName;
	
	private double rate;
	
	private long listenCount;
	
	
	
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

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}



	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public long getListenCount() {
		return listenCount;
	}

	public void setListenCount(long listenCount) {
		this.listenCount = listenCount;
	}
	
	
	
	
}
