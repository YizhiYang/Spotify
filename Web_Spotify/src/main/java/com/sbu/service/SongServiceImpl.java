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
import com.sbu.model.User;
import com.sbu.repository.SignupRepo;
import com.sbu.repository.SongRepo;

@Service("songService")
public class SongServiceImpl implements SongService {
	
	@Autowired
	private SongRepo songRepo;
	
	@Autowired
	private ContentFollowService contentFollowService;
	
	@Autowired
	private SignupRepo signupRepo;

	public boolean addSongToDatabase(Song song) {
		return songRepo.addSong(song);
	}

	public List<Song> getALLSongs() {
		return songRepo.getAllSongs();
	}
	
	public Song getSongByID(String songId) {
		List<Song> result = songRepo.getSongByID(songId);
		if(result==null || result.isEmpty()){
			return null;
		}
		return result.get(0);
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

	public void removeSong(String songId) {
		
		List<User> users = contentFollowService.getAllFollowersOfSong(songId);
		Song song = this.getSongByID(songId);
		for(int i = 0; i < users.size(); i++){   // !!!!!!!!!!      potential errror here. 
			users.get(i).getFollowedSongs().remove(song);
			signupRepo.saveUserToDB(users.get(i));
		}
		
		//REMOVE FROM ALL PLAYLISTS THAT HAS THIS SONG
		
	}

}
