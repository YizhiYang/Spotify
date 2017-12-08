package com.sbu.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sbu.model.User;
import com.sbu.service.AlbumService;
import com.sbu.service.ArtistService;
import com.sbu.service.ProfileService;
import com.sbu.service.ContentFollowService;
import com.sbu.service.GenericFileManageService;
import com.sbu.service.LoginService;
import com.sbu.service.PlaylistService;
import com.sbu.service.SignupService;
import com.sbu.service.SongService;

@Controller
public class BrowsePageController {
	
	public static final String PROFILE_IMAGE_PATH = "ProfileImages/";
	public static final String PROFILE_IMAGE_NAME = "profile.png";
	public static final String SONG_FILE_PATH = "Songs/";
	public static final String SONG_EXTENSION = ".mp3";
	public static final String REQUEST_SUCCESS = "success";
	public static final String REQUEST_FAILURE = "failure";
	public static final String FILE_NOT_FOUND_MESSAGE = "Sorry. The file you are looking for does not exist";
	public static final String ALBUMS_FILE_PATH = "AlbumImages/";
	public static final String ALBUMS_EXTENSION = ".jpg";
	
	@Autowired
	private SongService songService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private ProfileService changeProfileInfoService;
	@Autowired
	private SignupService signupService;

	
	@RequestMapping(value = "/getBrowsePageContent", method = RequestMethod.GET)
	public void getBrowsePageContent(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}

		String songsJsonString = songService.getAllSongsInJSON();
		String artistsJsonString = artistService.getAllArtistsInJSON();
		String albumsJsonString = albumService.getAllAlbumsInJSON();
		
		JSONObject totalJsonObject = new JSONObject();
		totalJsonObject.put("songsJson", songsJsonString);
		totalJsonObject.put("artistsJson", artistsJsonString);
		totalJsonObject.put("albumsJson", albumsJsonString);
		
	    response.getWriter().write(totalJsonObject.toString());
	}
	
	
	@RequestMapping(value = "/getUserPlaylist", method = RequestMethod.GET)
	public void getUserPlaylist(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		String jsonString = playlistService.getUserPlaylists(user);		
	    response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value = "/getFriendsPlaylist/{friendId}", method = RequestMethod.GET)
	public void getFriendPlaylist(HttpServletResponse response, HttpServletRequest request
			,@PathVariable("friendId") String friendId)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		String jsonString = playlistService.getUserPlaylists(signupService.getUserByID(friendId));		
	    response.getWriter().write(jsonString);
	}
	
	
	@RequestMapping(value = "/getUserProfile", method = RequestMethod.GET)
	public void getUserProfile(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			System.out.println("Kicked out of Session");
			return;
		}
		String jsonString = changeProfileInfoService.getUserInfoInJSON(user);
	    response.getWriter().write(jsonString);
	}
	
}
