package com.sbu.repository;

import java.util.List;

import com.sbu.model.ArtistUser;
import com.sbu.model.User;

public interface ArtistRepo {
	List<ArtistUser> getAllArtists();
	
	List<ArtistUser> getArtistByArtistID(String id);
	
	List<ArtistUser> getArtistByUser(User user);
	
	List<ArtistUser> getSearchArtistResults(String searchString);
	
	List<ArtistUser> getArtistsOfAlbum(long albumId);
	
	public boolean saveAristToDB(ArtistUser artist);
	
	public List checkArtistExist(String id);
	
	List<User> getAllFollowers(String artistId);
	
	void removeArtist(String artistId);

	List<ArtistUser> getRecommendArtist();
}
