package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Song;
import com.sbu.model.User;

public interface ContentFollowService {
	
	boolean addToUserFollowedSongs(User user, String songId);
	
	String getFollowedSongsInJSON(User user) throws JSONException;
	
	boolean addToUserFollowedAlbums(User user, String albumId);
	
	boolean addToUserFollowedArtists(User user, String artistId);
	
	String getFollowedAlbumsInJSON(User user) throws JSONException;
	
	String getFollowedArtistsInJSON(User user) throws JSONException;
	
	boolean removeFromFollowedSongs(User user, String songId);
	
	boolean removeFromFollowedAlbums(User user, String albumId);
	
	List<User> getAllFollowersOfSong(String songId);
	List<User> getAllFollowersOfAlbum(String albumId);
}
