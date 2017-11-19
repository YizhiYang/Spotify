package com.sbu.service;

import org.hibernate.Hibernate;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.Song;
import com.sbu.model.User;
import com.sbu.repository.GenericValidationRepo;

@Service("contentFollowService")
public class ContentFollowServiceImpl implements ContentFollowService {

	@Autowired
	SongService songService;
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	private GenericValidationRepo genericValidationRepo;
	
	
	
	public boolean addToUserFollowedSongs(User user, String songId) {
		User u = (User) genericValidationRepo.getUserByID(user.getId().toString()).get(0);
		Song song = songService.getSongByID(songId);
		if(u.getFollowedSongs().add(song)){
			genericValidationRepo.saveUserToDB(u);
		}
		return true;
	}


	public boolean addToUserFollowedAlbums(User user, String albumId) {
		User u = (User) genericValidationRepo.getUserByID(user.getId().toString()).get(0);
		Album album = albumService.getAlbumByID(albumId);
		if(u.getFollowedAlbums().add(album)){
			genericValidationRepo.saveUserToDB(u);
		}
		return true;
	}



	public boolean addToUserFollowedArtists(User user, String artistId) {
		User u = (User) genericValidationRepo.getUserByID(user.getId().toString()).get(0);
		ArtistUser artist = artistService.getArtistByArtistID(artistId);
		if(u.getFollowedArtist().add(artist)){
			genericValidationRepo.saveUserToDB(u);
		}
		return true;
	}
	
	
	public String getFollowedSongsInJSON(User user) throws JSONException {
		User u = (User) genericValidationRepo.getUserByID(user.getId().toString()).get(0);
		return songService.convertSongsToJSON(u.getFollowedSongs());
	}



	public String getFollowedAlbumsInJSON(User user) throws JSONException {
		User u = (User) genericValidationRepo.getUserByID(user.getId().toString()).get(0);
		return albumService.convertAlbumsToJSON(u.getFollowedAlbums());
	}



	public String getFollowedArtistsInJSON(User user) throws JSONException {
		User u = (User) genericValidationRepo.getUserByID(user.getId().toString()).get(0);
		return artistService.convertArtistsToJSON(u.getFollowedArtist());
	}

}
