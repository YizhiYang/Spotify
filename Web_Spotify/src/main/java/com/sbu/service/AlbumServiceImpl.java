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
import com.sbu.repository.AlbumRepo;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumRepo albumRepo;
	
	public List<Album> getAllAlbums() {
		return albumRepo.getAllAlbums();
	}

	public String getAllAlbumsInJSON() throws JSONException {
		List<Album> albums = getAllAlbums();
		
		return convertAlbumsToJSON(albums);
	}
	
	public Album getAlbumByID(String id){
		List<Album> result = albumRepo.getAlbumByID(id);
		if(result.isEmpty()){
			return null;
		}else{
			return result.get(0);
		}
	}

	public String convertAlbumsToJSON(List<Album> albums) throws JSONException {
		JSONArray jsonArray = new JSONArray();
		
		for(int i =0; i<albums.size(); i++){
			Album album = albums.get(i);
			List<ArtistUser> artists = album.getArtists();
			JSONArray artistNames = new JSONArray();
			for(int j=0; j< artists.size(); j++){
				artistNames.put(artists.get(j).getArtistName());
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("albumId", album.getAlbumId());
			jsonObject.put("albumName", album.getAlbumName());
			jsonObject.put("artistNames", artistNames);
			
			jsonArray.put(jsonObject);
		}
		
		return jsonArray.toString();
	}

	public String getSearchAlbumResultsInJSON(String searchString) throws JSONException {
		List<Album> albums = albumRepo.getSearchAlbumResults(searchString);
		return convertAlbumsToJSON(albums);
	}

}
