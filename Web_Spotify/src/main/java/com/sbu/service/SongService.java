package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Song;

public interface SongService {
	boolean addSongToDatabase(Song song);
	List<Song> getALLSongs();
	String getAllSongsInJSON() throws JSONException;
	
	String convertSongsToJSON(List<Song> songs) throws JSONException;
	
	String getSearchSongResultsInJSON(String searchString) throws JSONException;
	
}
