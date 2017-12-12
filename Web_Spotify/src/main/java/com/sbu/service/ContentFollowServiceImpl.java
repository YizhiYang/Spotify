package com.sbu.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.Song;
import com.sbu.model.User;
import com.sbu.repository.AlbumRepo;
import com.sbu.repository.ArtistRepo;
import com.sbu.repository.SignupRepo;
import com.sbu.repository.SongRepo;

@Service("contentFollowService")
public class ContentFollowServiceImpl implements ContentFollowService {

	@Autowired
	SongService songService;
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	private SignupRepo signupRepo;
	
	@Autowired
	private SongRepo songRepo;
	
	@Autowired
	private AlbumRepo albumRepo;
	
	@Autowired
	private ArtistRepo artistRepo;
	
	public boolean addToUserFollowedSongs(User user, String songId) {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		Song song = songService.getSongByID(songId);
		if(u.getFollowedSongs().add(song)){
			signupRepo.saveUserToDB(u);
		}
		return true;
	}


	public boolean addToUserFollowedAlbums(User user, String albumId) {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		Album album = albumService.getAlbumByID(albumId);
		if(u.getFollowedAlbums().add(album)){
			signupRepo.saveUserToDB(u);
		}
		return true;
	}



	public boolean addToUserFollowedArtists(User user, String artistId) {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		ArtistUser artist = artistService.getArtistByArtistID(artistId);
		if(u.getFollowedArtists().add(artist)){
			signupRepo.saveUserToDB(u);
		}
		return true;
	}
	
	
	public String getFollowedSongsInJSON(User user) throws JSONException {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		return songService.convertSongsToJSON(u.getFollowedSongs());
	}



	public String getFollowedAlbumsInJSON(User user) throws JSONException {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		return albumService.convertAlbumsToJSON(u.getFollowedAlbums());
	}



	public String getFollowedArtistsInJSON(User user) throws JSONException {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		return artistService.convertArtistsToJSON(u.getFollowedArtists());
	}


	public boolean removeFromFollowedSongs(User user, String songId) {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		Song songToUnfollow = null;
		for(Song song: u.getFollowedSongs()){
			if(song.getSongId().equals(Long.valueOf(songId))){
				songToUnfollow = song;
			}
		}
		u.getFollowedSongs().remove(songToUnfollow);
		signupRepo.saveUserToDB(u);
		return true;
	}
	
	public boolean removeFromFollowedAlbums(User user, String albumId) {
		// unfollow albums
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		Album albumToUnfollow = null;
		
		for(Album album: u.getFollowedAlbums()){
			if(album.getAlbumId().equals(Long.valueOf(albumId))){
				albumToUnfollow = album;
			}else{
				System.out.println(album.getAlbumId()+", "+Long.valueOf(albumId));
			}
		}
		System.out.println(albumToUnfollow);
		u.getFollowedAlbums().remove(albumToUnfollow);
		signupRepo.saveUserToDB(u);
		return true;
	}

	public List<User> getAllFollowersOfSong(String songId) {
		return songRepo.getAllFollowers(songId);
	}
	
	public List<User> getAllFollowersOfAlbum(String albumId) {
		return albumRepo.getAllFollowers(albumId);
	}


	public boolean removeFromFollowedArtists(User user, String artistId) {
		
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		ArtistUser artistToUnfollow = null;
		
		for(ArtistUser artistUser: u.getFollowedArtists()){
			if(artistUser.getArtistID() == Long.valueOf(artistId)){
				artistToUnfollow = artistUser;
			}
		}
		u.getFollowedArtists().remove(artistToUnfollow);
		signupRepo.saveUserToDB(u);
		return true;
		
	}


	public List<User> getAllFollowersOfArtist(String artistId) {
		return artistRepo.getAllFollowers(artistId);
	}

}
