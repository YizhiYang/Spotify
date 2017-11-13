package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.ArtistUser;

public interface ArtistService {
	List<ArtistUser> getAllArtists();
	
	String getAllArtistsInJSON() throws JSONException;
}
