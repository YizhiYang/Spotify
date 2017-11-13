package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Song;

public interface SongUploadDownloadService {
	boolean addSongToDatabase(Song song);
	String findSongFileBasedOnID(Long id);
	List<Song> getALLSongs();
	String getAllSongsInJSON() throws JSONException;
	
}
