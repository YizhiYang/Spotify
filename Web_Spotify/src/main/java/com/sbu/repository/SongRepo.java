package com.sbu.repository;

import java.util.List;

import com.sbu.model.Song;

public interface SongRepo {
	boolean addSong(Song song);
	
	List<Song> getAllSongs();
	
	List<Song> getSearchSongResults(String searchString);

}
