package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.ArtistUser;

public interface ArtistService {
	
	boolean saveArtist(ArtistUser artist);
	
	boolean makeNewArtist(String userID, String artistName);
	
	ArtistUser checkArtistExist(String id);
	
	List<ArtistUser> getAllArtists();
	
	String getAllArtistsInJSON() throws JSONException;
	
	String convertArtistsToJSON(List<ArtistUser> artists) throws JSONException;
	
	ArtistUser getArtistByArtistID(String id);
	
	String getSearchArtistResultsInJSON(String searchString) throws JSONException;
	
	List<ArtistUser> getArtistsOfAlbum(long albumId);
}
