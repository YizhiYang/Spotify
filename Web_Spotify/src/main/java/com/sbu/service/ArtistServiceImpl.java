package com.sbu.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.repository.ArtistRepo;


@Service("artistService")
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	private ArtistRepo artistRepo;
	
	public List<ArtistUser> getAllArtists() {
		return artistRepo.getAllArtists();
	}

	public String getAllArtistsInJSON() throws JSONException {
		List<ArtistUser> artists = getAllArtists();
		JSONArray jsonArray = new JSONArray();
		
		for(int i=0; i<artists.size(); i++){
			ArtistUser artist = artists.get(i);
			
			int totalNumberOfSongs = 0;
			List<Album> albums = artist.getAlbum();
			for(int j=0; j<albums.size(); j++){
				totalNumberOfSongs += albums.get(i).getNumOfSongs();
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ArtistName", artist.getArtistName());
			jsonObject.put("userName", artist.getUser().getUserName());
			jsonObject.put("totalNumberOfSongs", totalNumberOfSongs);
			
			jsonArray.put(jsonObject);
		}
		
		return jsonArray.toString();
	}

}
