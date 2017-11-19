package com.sbu.repository;

import java.util.List;

import com.sbu.model.Playlist;
import com.sbu.model.User;

public interface PlaylistRepo {
	boolean saveNewPlaylist(Playlist playlist);
	List<Playlist> getUserPlaylists(User user);
	public List<Playlist> getPlaylistById(Long plId);
}
