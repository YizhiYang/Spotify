package com.sbu.repository;

import java.util.List;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;

public interface GenericValidationRepo {
	
	public List checkArtistExist(String id);
	
	public boolean saveAlbumToDB(Album album);
	
	public boolean saveAristToDB(ArtistUser artist);
}
