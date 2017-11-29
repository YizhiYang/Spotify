package com.sbu.repository;

import java.util.List;

import com.sbu.model.Playlist;
import com.sbu.model.User;

public interface PlaylistRepo {
	
	boolean saveNewPlaylist(Playlist playlist);
	
	List<Playlist> getUserPlaylists(User user);
	
	List<Playlist> getPlaylistById(Long plId);
	
	boolean renamePlaylist(Playlist pl);
	
	void removePlaylist(Long plId);
	
	List<Playlist> getPlaylistsContainSong(String songId);
}
