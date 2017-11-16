package com.sbu.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.Song;
import com.sbu.repository.SongRepo;

@Service("songService")
public class SongServiceImpl implements SongService {
	
	@Autowired
	private SongRepo songRepo;

	public boolean addSongToDatabase(Song song) {
		return songRepo.addSong(song);
	}

	public List<Song> getALLSongs() {
		return songRepo.getAllSongs();
	}

	public String getAllSongsInJSON() throws JSONException {
		List<Song> songs = getALLSongs();
		return convertSongsToJSON(songs);	
	}

	public String convertSongsToJSON(List<Song> songs) throws JSONException {
		JSONArray jsonArray = new JSONArray();
		
		for(int i =0; i<songs.size(); i++){
			Song song = songs.get(i);
			Album album = song.getAlbum();
			List<ArtistUser> artists = album.getArtists();

			JSONArray artistNames = new JSONArray();
			for(int j=0; j< artists.size(); j++){
				artistNames.put(artists.get(j).getArtistName());
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("songId", song.getSongId());
			jsonObject.put("songName", song.getSongName());
			jsonObject.put("albumName", album.getAlbumName());
			jsonObject.put("artistNames", artistNames);
			jsonObject.put("duration", song.getDuration());
			
			jsonArray.put(jsonObject);
		}
		
		return jsonArray.toString();
	}

	public String getSearchSongResultsInJSON(String searchString) throws JSONException {
		List<Song> songs = songRepo.getSearchSongResults(searchString);
		return convertSongsToJSON(songs);
	}

}
