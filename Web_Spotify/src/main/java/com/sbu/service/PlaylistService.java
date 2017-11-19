package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Playlist;
import com.sbu.model.User;

public interface PlaylistService {
	boolean makeNewPlaylist(User user, String playlistName);
	String getUserPlaylistsInJSON(User user) throws JSONException;
	
	
	String convertPlaylistsToJSON(List<Playlist> playlists) throws JSONException;
}
