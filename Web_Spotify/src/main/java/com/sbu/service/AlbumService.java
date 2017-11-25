package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Album;
import com.sbu.model.Song;

public interface AlbumService {
	boolean saveAlbum(Album album);
	
	List<Album> getAllAlbums();
	
	String getAllAlbumsInJSON() throws JSONException;
	
	Album getAlbumByID(String id);
	
	String convertAlbumsToJSON(List<Album> albums) throws JSONException;
	
	String searchAlbums(String searchString) throws JSONException;
	
	boolean removeAlbum(String albumID);
}
