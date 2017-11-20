package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Playlist;
import com.sbu.model.User;

public interface PlaylistService {
	boolean makeNewPlaylist(User user, String playlistName);
	
	String getUserPlaylistsInJSON(User user) throws JSONException;
	
	boolean addSongToPlaylist(long playlistId, long songId);
	
	String convertPlaylistsToJSON(List<Playlist> playlists) throws JSONException;
	
	public String getPlaylistSongsInJSON(long playlistId) throws JSONException;
	
	public Boolean renamePlaylist(String newName, String playlistId);
	
	public Boolean removePlayList(String playlistId);
}
