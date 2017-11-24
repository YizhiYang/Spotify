package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Album;
import com.sbu.model.Song;

public interface AlbumService {
	public boolean saveAlbum(Album album);
	List<Album> getAllAlbums();
	String getAllAlbumsInJSON() throws JSONException;
	public Album getAlbumByID(String id);
	public String convertAlbumsToJSON(List<Album> albums) throws JSONException;
	
	String getSearchAlbumResultsInJSON(String searchString) throws JSONException;
	
	public boolean removeAlbum(String albumID);
}
