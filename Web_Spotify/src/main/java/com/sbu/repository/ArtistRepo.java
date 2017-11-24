package com.sbu.repository;

import java.util.List;

import com.sbu.model.ArtistUser;

public interface ArtistRepo {
	List<ArtistUser> getAllArtists();
	List<ArtistUser> getArtistByArtistID(String id);
	
	List<ArtistUser> getSearchArtistResults(String searchString);
	
	List<ArtistUser> getArtistsOfAlbum(long albumId);
	
	public boolean saveAristToDB(ArtistUser artist);
	
	public List checkArtistExist(String id);
}
