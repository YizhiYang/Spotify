package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Song;
import com.sbu.model.User;

public interface SongService {
	
	boolean addSongToDatabase(Song song);
	
	List<Song> getALLSongs();
	
	Song getSongByID(String songId);
	
	String getAllSongsInJSON() throws JSONException;
	
	String getAllPendingSongsInJSON() throws JSONException;
	
	String convertSongsToJSON(List<Song> songs) throws JSONException;
	
	String searchSongs(String searchString) throws JSONException;
	
	void removeSong(String songId);
	
	void approveSong(String songId);
	
	void addSongToPlayHistory(User user, Song song);
	
	void removeSongFromPlayHistory(User user, Song song);
	
	String getPlayHistoryInJSON(User user) throws JSONException;
	
	List<User> getAllUsersPlayedSong(String songId);
	
	String getTopSongsOfGenre(String genre) throws JSONException;

	String getMostOccur(List<Song> allSongs);
}
