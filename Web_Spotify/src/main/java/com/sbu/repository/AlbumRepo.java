package com.sbu.repository;

import java.util.List;

import com.sbu.model.Album;

public interface AlbumRepo {
	List<Album> getAllAlbums();
	List<Album> getAlbumByID(String id);
	
	List<Album> getSearchAlbumResults(String searchString);
}
