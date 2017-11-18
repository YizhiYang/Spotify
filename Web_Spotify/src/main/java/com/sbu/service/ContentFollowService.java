package com.sbu.service;

import org.json.JSONException;

import com.sbu.model.User;

public interface ContentFollowService {
	boolean addToUserFollowedSongs(User user, String songId);
	String getFollowedSongsInJSON(User user) throws JSONException;
	
	boolean addToUserFollowedAlbums(User user, String albumId);
	boolean addToUserFollowedArtists(User user, String artistId);
	
	String getFollowedAlbumsInJSON(User user) throws JSONException;
	String getFollowedArtistsInJSON(User user) throws JSONException;
}
