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
		return convertArtistsToJSON(artists);
	}

	public String convertArtistsToJSON(List<ArtistUser> artists) throws JSONException {
		JSONArray jsonArray = new JSONArray();
		
		for(int i=0; i<artists.size(); i++){
			ArtistUser artist = artists.get(i);
			
			int totalNumberOfSongs = 0;
			List<Album> albums = artist.getAlbum();
			System.out.println(albums.size());
			for(int j=0; j<albums.size(); j++){
				totalNumberOfSongs += albums.get(j).getNumOfSongs();
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("artistID", artist.getArtistID());
			jsonObject.put("artistName", artist.getArtistName());
			jsonObject.put("userName", artist.getUser().getUserName());
			jsonObject.put("totalNumberOfSongs", totalNumberOfSongs);
			
			jsonArray.put(jsonObject);
		}
		
		return jsonArray.toString();
	}

	public ArtistUser getArtistByArtistID(String id) {
		List<ArtistUser> artists = artistRepo.getArtistByArtistID(id);
		if(artists.isEmpty()){
			return null;
		}else{
			return artists.get(0);
		}
	}

}
