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
	
	
	private int durationSecs;
	
	private URL file;
	
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

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}



	public int getDurationSecs() {
		return durationSecs;
	}

	public void setDurationSecs(int durationSecs) {
		this.durationSecs = durationSecs;
	}

	public URL getFile() {
		return file;
	}

	public void setFile(URL file) {
		this.file = file;
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
