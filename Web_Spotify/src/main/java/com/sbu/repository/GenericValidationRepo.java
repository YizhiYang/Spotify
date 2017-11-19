package com.sbu.repository;

import java.util.List;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.User;

public interface GenericValidationRepo {
	
	public List checkArtistExist(String id);
	
	public boolean saveAlbumToDB(Album album);
	
	public List getUserByID(String id);
	
	public boolean saveAristToDB(ArtistUser artist);
	
	public boolean saveUserToDB(User user);
}
