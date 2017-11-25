package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Playlist;
import com.sbu.model.User;

public interface PlaylistService {
	
	boolean makeNewPlaylist(User user, String playlistName);
	
	String getUserPlaylistsInJSON(User user) throws JSONException;
	
	boolean addSongToPlaylist(long playlistId, long songId);
	
	boolean removeSongFromPlaylist(long playlistId, long songId);
	
	String convertPlaylistsToJSON(List<Playlist> playlists) throws JSONException;
	
	String getPlaylistSongsInJSON(long playlistId) throws JSONException;
	
	Boolean renamePlaylist(String newName, String playlistId);
	
	Boolean removePlayList(String playlistId);
	
	List<Playlist> getPlaylistsThatContainsSong(String songId);
}
