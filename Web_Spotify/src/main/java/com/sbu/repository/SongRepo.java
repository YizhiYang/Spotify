package com.sbu.repository;

import java.util.List;

import com.sbu.model.Song;
import com.sbu.model.User;

public interface SongRepo {
	boolean addSong(Song song);
	
	List<Song> getAllSongs();
	List<Song> getSongByID(String songId);
	
	List<Song> getSearchSongResults(String searchString);
	List<User> getAllFollowers(String songId);

}
