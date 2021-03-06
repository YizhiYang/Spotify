package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.User;

public interface ArtistService {
	
	boolean saveArtist(ArtistUser artist);
	
	boolean makeNewArtist(String userID, String artistName);
	
	ArtistUser checkArtistExist(String id);
	
	List<ArtistUser> getAllArtists();
	
	String getAllArtistsInJSON() throws JSONException;
	
	String convertArtistsToJSON(List<ArtistUser> artists) throws JSONException;
	
	ArtistUser getArtistByArtistID(String id);
	
	ArtistUser getArtistByUser(User user);
	
	String searchArtists(String searchString) throws JSONException;
	
	List<ArtistUser> getArtistsOfAlbum(long albumId);
	
	void removeArtist(String artistId);

	List<ArtistUser> getRecommendArtist();
	
	List<ArtistUser> getRelatedArtist(String gen, String selfId);
	
	void addArtist(String imageURL, String artistName, String artistBio);
}
