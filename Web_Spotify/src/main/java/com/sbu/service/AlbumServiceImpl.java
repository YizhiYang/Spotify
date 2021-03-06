package com.sbu.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.controller.AlbumsController;
import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.Song;
import com.sbu.model.User;
import com.sbu.repository.AlbumRepo;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumRepo albumRepo;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private SongService songService;
	@Autowired
	private ContentFollowService contentFollowService;
	
	public boolean saveAlbum(Album album) {
		return albumRepo.saveAlbumToDB(album);
	}
	
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
			jsonObject.put("albumImageUrl", album.getAlbumImageUrl());
			
			File file = null;
	        ClassLoader classloader = Thread.currentThread().getContextClassLoader(); 
			String albumFileName = null;    
			albumFileName = album.getAlbumId() + AlbumsController.ALBUMS_EXTENSION;
	        String albumPath = classloader.getResource(AlbumsController.ALBUMS_FILE_PATH).getPath();      
	        file = new File(albumPath+albumFileName);
	        if(file.exists()){
	        	jsonObject.put("imageType", "file");
	        }else{
	        	jsonObject.put("imageType", "url");
	        }
			
			jsonArray.put(jsonObject);
		}
		
		return jsonArray.toString();
	}

	public String searchAlbums(String searchString) throws JSONException {
		List<Album> albums = albumRepo.getSearchAlbumResults(searchString);
		return convertAlbumsToJSON(albums);
	}

	public boolean removeAlbum(String albumID) {
		long albumIdLong = Long.valueOf(albumID);
		//REMOVE SONGS IN ALBUM
		List<Song> songs = getAlbumByID(albumID).getSongs();
		for(int i=0; i<songs.size(); i++){
			songService.removeSong(String.valueOf(songs.get(i).getSongId()));
		}
		//REMOVE ALBUM ARTIST RELATION
		List<ArtistUser> artists= artistService.getArtistsOfAlbum(albumIdLong);
		for(ArtistUser artist: artists){
			Album albumToRemove = null;
			for(Album album: artist.getAlbum()){
				if(album.getAlbumId() == albumIdLong){
					albumToRemove = album;
				}
			}
			artist.getAlbum().remove(albumToRemove);
			artistService.saveArtist(artist);
		}
		//REMOVE FROM ALL FOLLOWED ALBUM FOR ALL USERS WHO FOLLOWS THIS ALBUM
		List<User> users = contentFollowService.getAllFollowersOfAlbum(albumID);
		for(User u: users){
			contentFollowService.removeFromFollowedAlbums(u, albumID);
		}
		//REMOVE ALBUM
		albumRepo.removeAlbum(albumIdLong);
		return true;
	}

	public String getOwnedAlbumIds(User user) {
		ArtistUser artist = artistService.getArtistByUser(user);
		if(artist!=null){
			String albumIds = "";
			for(Album album: artist.getAlbum()){
				albumIds += album.getAlbumId() + ",";
			}
			return albumIds.substring(0, albumIds.length()-1);
		}else{
			return "";
		}
		
	}
	
	public List<Album> getRecommendAlbums(){
		return albumRepo.getRecommendAlbums();
	}
	
	public String getMostOccur(Album album){
		List<Song> songs = album.getSongs();
		HashMap<String, Integer> map = new HashMap();
		for(Song song: songs){
			if(!map.containsKey(song.getSongGenre())){
				map.put(song.getSongGenre(), 1);
			}
			else{
				map.put(song.getSongGenre(), map.get(song.getSongGenre())+1);
			}
		}
		Map.Entry<String, Integer> maxEntry = null;
		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		if(maxEntry == null){
			return null;
		}
		return maxEntry.getKey();	
	}

}
