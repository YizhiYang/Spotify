package com.sbu.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="recentlyPlayedList")
public class RecentlyPlayedList {
	
	@Id
	@GeneratedValue
	private Long playlistId;
	
	@ManyToMany
	@JoinTable(name="JoinRecentlyPlayedListAndSong", joinColumns={@JoinColumn(name="playlistId")}, inverseJoinColumns={@JoinColumn(name="songId")})
	private Collection<Song> songs;

	public Collection<Song> getSongs() {
		return songs;
	}

	public void setSongs(Collection<Song> songs) {
		this.songs = songs;
	}
	
	
	
	
}
