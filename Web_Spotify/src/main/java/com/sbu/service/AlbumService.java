package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Album;

public interface AlbumService {
	List<Album> getAllAlbums();
	String getAllAlbumsInJSON() throws JSONException;
	public Album getAlbumByID(String id);
}
