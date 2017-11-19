package com.sbu.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.Playlist;
import com.sbu.model.Song;
import com.sbu.model.User;
import com.sbu.repository.GenericValidationRepo;
import com.sbu.repository.PlaylistRepo;

@Service("playlistService")
public class PlaylistServiceImpl implements PlaylistService {

	@Autowired
	PlaylistRepo playlistRepo;
	
	@Autowired
	SongService songService;
	
	@Autowired
	GenericValidationRepo genericValidationRepo;
	
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

	public boolean addSongToPlaylist(long playlistId, long songId) {
		
		List<Playlist> playlists = playlistRepo.getPlaylistById(playlistId);
		Playlist playlist = playlists.get(0);
		
		Song song = songService.getSongByID(String.valueOf(songId));
		playlist.getSongs().add(song);
		
		if(playlistRepo.saveNewPlaylist(playlist))
			return true;
		else
			return false;
	}
	
	public String getPlaylistSongsInJSON(long playlistId) throws JSONException{
		
		List<Playlist> playlists = playlistRepo.getPlaylistById(playlistId);
		Playlist playlist = playlists.get(0);
		List<Song> songs = playlist.getSongs();
		
		return songService.convertSongsToJSON(songs);
	}

	public Boolean renamePlaylist(String newName, String playlistId) {

		long plId = Long.parseLong(playlistId);
		List<Playlist> playlists = playlistRepo.getPlaylistById(plId);
		Playlist playlist = playlists.get(0);
		playlist.setName(newName);
		

		return playlistRepo.renamePlaylist(playlist);
	}

}
