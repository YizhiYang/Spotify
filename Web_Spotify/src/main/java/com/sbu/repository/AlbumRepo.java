package com.sbu.repository;

import java.util.List;

import com.sbu.model.Album;
import com.sbu.model.User;

public interface AlbumRepo {
	List<Album> getAllAlbums();
	List<Album> getAlbumByID(String id);
	
	List<Album> getSearchAlbumResults(String searchString);
	
	void removeAlbum(Long albumId);
	
	public boolean saveAlbumToDB(Album album);
	List<User> getAllFollowers(String albumId);
}
