package com.sbu.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.Playlist;
import com.sbu.model.User;
import com.sbu.repository.PlaylistRepo;

@Service("playlistService")
public class PlaylistServiceImpl implements PlaylistService {

	@Autowired
	PlaylistRepo playlistRepo;
	
	public boolean makeNewPlaylist(User user, String playlistName) {
		Playlist playlist = new Playlist();
		playlist.setName(playlistName);
		playlist.setOwner(user);
		playlistRepo.saveNewPlaylist(playlist);
		return true;
		
	}

	public String getUserPlaylistsInJSON(User user) throws JSONException{
		List<Playlist> playlists = playlistRepo.getUserPlaylists(user);
		return convertPlaylistsToJSON(playlists);
	}

	public String convertPlaylistsToJSON(List<Playlist> playlists) throws JSONException {
		JSONArray jsonArray = new JSONArray();	
		for(int i =0; i<playlists.size(); i++){
			Playlist playlist = playlists.get(i);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("playlistID", playlist.getId());
			jsonObject.put("playlistName", playlist.getName());
			
			jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
	}

}
