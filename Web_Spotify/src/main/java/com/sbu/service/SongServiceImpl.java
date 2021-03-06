package com.sbu.service;

import java.io.File;
import java.util.ArrayList;
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
import com.sbu.model.Playlist;
import com.sbu.model.Song;
import com.sbu.model.User;
import com.sbu.repository.PlaylistRepo;
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
	
	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private ArtistService artistService;
	
	

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
			//DETERMINE IF SONG IS APPROVED
			if(song.isApproved()){
			
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
				jsonObject.put("songGenre", song.getSongGenre());
				jsonObject.put("listenCount", song.getListenCount());
				
				jsonObject.put("albumId", album.getAlbumId());
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
		}
		
		return jsonArray.toString();
	}

	public String searchSongs(String searchString) throws JSONException {
		List<Song> songs = songRepo.getSearchSongResults(searchString);
		return convertSongsToJSON(songs);
	}

	public void removeSong(String songId) {
		
		List<User> users = contentFollowService.getAllFollowersOfSong(songId);
		Song song = this.getSongByID(songId);
		for(int i = 0; i < users.size(); i++){   
			contentFollowService.removeFromFollowedSongs(users.get(i), songId);
		}
		
		List<User> users2 = getAllUsersPlayedSong(songId);
		for(int i = 0; i < users2.size(); i++){   
			removeSongFromPlayHistory(users2.get(i), song);
		}
		
		//REMOVE FROM ALL PLAYLISTS THAT HAS THIS SONG
		
		List<Playlist> lists = playlistService.getPlaylistsContainSong(songId);
		
		for(int i = 0; i < lists.size(); i++){
			playlistService.removeSongFromPlaylist(lists.get(i).getId(), Long.valueOf(songId));
		}
		songRepo.removeSong(songId);
	}

	public String getAllPendingSongsInJSON() throws JSONException {
		List<Song> songs = getALLSongs();
		
		JSONArray jsonArray = new JSONArray();
		
		for(int i =0; i<songs.size(); i++){
			Song song = songs.get(i);
			//DETERMINE IF SONG IS APPROVED
			if(!song.isApproved()){
			
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
				
				jsonObject.put("albumId", album.getAlbumId());
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
		}
		return jsonArray.toString();
	}

	public void approveSong(String songId) {
		Song song = this.getSongByID(songId);
		song.setApproved(true);
		//PERSIST SONG
		songRepo.addSong(song);	
	}

	public void addSongToPlayHistory(User user, Song song) {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		for(Song s: u.getPlayedSongsHistory()){
			if(s.getSongId()==song.getSongId()){
				return;
			}
		}
		u.getPlayedSongsHistory().add(song);
		signupRepo.saveUserToDB(u);
	}

	public void removeSongFromPlayHistory(User user, Song song) {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		Song songToBeRemovedFromHistory = null;
		for(Song s: u.getPlayedSongsHistory()){
			if(s.getSongId()==song.getSongId()){
				songToBeRemovedFromHistory = s;
			}
		}
		u.getPlayedSongsHistory().remove(songToBeRemovedFromHistory);
		signupRepo.saveUserToDB(u);
	}

	public String getPlayHistoryInJSON(User user) throws JSONException {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		return convertSongsToJSON(u.getPlayedSongsHistory());
	}

	public List<User> getAllUsersPlayedSong(String songId) {
		return songRepo.getAllUsersWhoPlayedSong(songId);
	}

	public String getTopSongsOfGenre(String genre) throws JSONException {
		String genreReplaceSpace = null;
		String genreReplaceSpace2 = null;
		String genreReplaceDash = null;
		String genreReplaceDash2 = null;
		if(genre.contains(" ")){
			genreReplaceSpace = genre.replaceAll(" ", "-");
			genreReplaceSpace2 = genre.replaceAll(" ", "");
		}
		if(genre.contains("-")){
			genreReplaceDash = genre.replaceAll("-", "");
			genreReplaceDash2 = genre.replaceAll("-", " ");
		}

		List<Song> songs = new ArrayList<Song>();
		songs.addAll(songRepo.getTopSongsByGenre(genre));
		if(genreReplaceSpace != null){
			songs.addAll(songRepo.getTopSongsByGenre(genreReplaceSpace));
			songs.addAll(songRepo.getTopSongsByGenre(genreReplaceSpace2));
		}
		if(genreReplaceDash != null){
			songs.addAll(songRepo.getTopSongsByGenre(genreReplaceDash));
			songs.addAll(songRepo.getTopSongsByGenre(genreReplaceDash2));
		}

		return convertSongsToJSON(songs);
	}
	
	public String getMostOccur(List<Song> allSongs){
		HashMap<String, Integer> map = new HashMap();
		for(Song song: allSongs){
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
	
	public List<Song> getEditorSongs(){
		
		return songRepo.getEditorSongs();
	}

	public String getOwnedSongIds(User user) {
		ArtistUser artist = artistService.getArtistByUser(user);
		if(artist!=null){
			String songIds = "";
			for(Album album: artist.getAlbum()){
				for(Song song: album.getSongs()){
					songIds += song.getSongId() + ",";
				}
			}
			return songIds.substring(0, songIds.length()-1);
		}else{
			return "";
		}
	}
}
