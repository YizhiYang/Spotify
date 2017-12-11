package com.sbu.service;

import java.util.ArrayList;
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
import com.sbu.model.UserType;
import com.sbu.repository.ArtistRepo;
import com.sbu.repository.SignupRepo;


@Service("artistService")
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	private ArtistRepo artistRepo;
	@Autowired
	private SongService songService;
	@Autowired
	private SignupRepo signupRepo;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private ContentFollowService contentFollowService;
	
	public boolean saveArtist(ArtistUser artist) {
		return artistRepo.saveAristToDB(artist);
	}
	
	public boolean makeNewArtist(String userID, String artistName) {
		List userResult = signupRepo.getUserByID(userID);
		if(userResult.isEmpty()){
			return false;
		}else{
			User u = (User)(userResult.get(0));
			ArtistUser artist = new ArtistUser();
			artist.setUser(u);;
			u.setUserType(UserType.ARTIST);
			signupRepo.saveUserToDB(u);
			artist.setArtistName(artistName);
			artistRepo.saveAristToDB(artist);
			return true;
		}
		
	}
	
	public ArtistUser checkArtistExist(String id){
		
		return (ArtistUser)artistRepo.checkArtistExist(id).get(0);
	}
	
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
				totalNumberOfSongs += albums.get(j).getSongs().size();
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("artistID", artist.getArtistID());
			jsonObject.put("artistName", artist.getArtistName());
			
			
			//DETERMINE IF USE IMAGE FROM OTHER SOURCE OR FROM OWN SERVER
			if(artist.getUser()!=null){
				jsonObject.put("userName", artist.getUser().getUserName());
				jsonObject.put("imageType", "file");
			}else{
				jsonObject.put("artistImageUrl", artist.getArtistImageUrl());
				jsonObject.put("imageType", "url");
			}
			
			
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
	
	public ArtistUser getArtistByUser(User user) {
		List<ArtistUser> artists = artistRepo.getArtistByUser(user);
		if(artists.isEmpty()){
			return null;
		}else{
			return artists.get(0);
		}
	}

	public String searchArtists(String searchString) throws JSONException {
		List<ArtistUser> artists = artistRepo.getSearchArtistResults(searchString);
		return convertArtistsToJSON(artists);
	}

	public List<ArtistUser> getArtistsOfAlbum(long albumId) {
		return artistRepo.getArtistsOfAlbum(albumId);
	}

	public void removeArtist(String artistId) {
		//REMOVE ALL ALBUM OF THIS ARTIST
		ArtistUser artist = getArtistByArtistID(artistId);
		for(int i=0; i<artist.getAlbum().size();i++){
			albumService.removeAlbum(artist.getAlbum().get(i).getAlbumId().toString());
		}
		//REMOVE ALL USER FOLLOW ARTIST RELATION
		List<User> users = contentFollowService.getAllFollowersOfArtist(artistId);
		for(User u: users){
			contentFollowService.removeFromFollowedArtists(u, artistId);
		}
		artistRepo.removeArtist(artistId);
	}
	
	public List<ArtistUser> getRecommendArtist(){
		
		return artistRepo.getRecommendArtist();
		
	}
	
	public List<ArtistUser> getRelatedArtist(String gen, String selfId){
		
		List<ArtistUser> allArtists = this.getAllArtists();
		List<ArtistUser> relatedArtists = new ArrayList();
		
		for(ArtistUser artist: allArtists){
			
			List<Album> albums = artist.getAlbum();
		
			List<Song> allSongs = new ArrayList();
		
			for(Album album: albums){
				List<Song> songs = album.getSongs();
				allSongs.addAll(songs);
			}
			String genre = songService.getMostOccur(allSongs);
			if(genre != null){
				if(genre.equals(gen) && !selfId.equals(String.valueOf(artist.getArtistID()))){
					relatedArtists.add(artist);
				}
			}
		}
		
		return relatedArtists;
	}

	public void addArtist(String imageURL, String artistName, String artistBio) {
		ArtistUser artist = new ArtistUser();
		artist.setArtistImageUrl(imageURL);
		artist.setArtistName(artistName);
		artist.setBio(artistBio);
		artistRepo.saveAristToDB(artist);
		
	}

}
