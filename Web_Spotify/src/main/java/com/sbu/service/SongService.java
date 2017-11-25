package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Song;

public interface SongService {
	
	boolean addSongToDatabase(Song song);
	
	List<Song> getALLSongs();
	
	Song getSongByID(String songId);
	
	String getAllSongsInJSON() throws JSONException;
	
	String convertSongsToJSON(List<Song> songs) throws JSONException;
	
	String searchSongs(String searchString) throws JSONException;
	
	void removeSong(String songId);
}
