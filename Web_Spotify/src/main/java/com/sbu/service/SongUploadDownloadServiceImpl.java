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
import com.sbu.repository.SongUploadDownloadRepo;

@Service("songUploadService")
public class SongUploadDownloadServiceImpl implements SongUploadDownloadService {
	
	@Autowired
	private SongUploadDownloadRepo songUploadRepo;

	public boolean addSongToDatabase(Song song) {
		return songUploadRepo.addSong(song);
	}

	public String findSongFileBasedOnID(Long id) {
		String fileName = songUploadRepo.findSongFileNameBasedOnIDInDatabase(id);
		System.out.println(fileName);
		return fileName;
	}

	public List<Song> getALLSongs() {
		return songUploadRepo.getAllSongs();
	}

	public String getAllSongsInJSON() throws JSONException {
		List<Song> songs = getALLSongs();

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
	
	
	

}
