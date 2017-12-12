package com.sbu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
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
public class PlaylistController {
	
	public static final String PROFILE_IMAGE_PATH = "ProfileImages/";
	public static final String PROFILE_IMAGE_NAME = "profile.png";
	
	//SONG FILE PATH
	public static final String SONG_FILE_PATH = "Songs/";
	public static final String SONG_EXTENSION = ".mp3";
	public static final String REQUEST_SUCCESS = "success";
	public static final String REQUEST_FAILURE = "failure";
	public static final String FILE_NOT_FOUND_MESSAGE = "Sorry. The file you are looking for does not exist";
	
	
	//ALBUM
	public static final String ALBUMS_FILE_PATH = "AlbumImages/";
	public static final String ALBUMS_EXTENSION = ".jpg";
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private SignupService signupService;
	@Autowired
	private SongService songService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private ContentFollowService contentFollowService;
	
	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private ProfileService changeProfileInfoService;

	@Autowired
	private GenericFileManageService fileManager;
	
	@RequestMapping(value = "/addNewPlaylist", method = RequestMethod.POST)
	public void addNewPlaylist(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}	
		String playlistName = request.getParameter("playlistName");
		String privateList = request.getParameter("private");
		
		if(playlistService.makeNewPlaylist(user, playlistName, privateList)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
	
	@RequestMapping(value="/addSongToPlaylist/{playlistId}/{songId}", method = RequestMethod.POST)
	public void addSongToPlaylist(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("playlistId") String playlistId, @PathVariable("songId") String songId) throws IOException{
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		long plId = Long.parseLong(playlistId);
		long sId = Long.parseLong(songId);
		if(playlistService.addSongToPlaylist(plId, sId)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
		
	}
	
	@RequestMapping(value="/removeSongFromPlaylist/{playlistId}/{songId}", method = RequestMethod.POST)
	public void removeSongFromPlaylist(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("playlistId") String playlistId, @PathVariable("songId") String songId) throws IOException{
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		long plId = Long.parseLong(playlistId);
		long sId = Long.parseLong(songId);	
		if(playlistService.removeSongFromPlaylist(plId, sId)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
		
	}
	
	@RequestMapping(value="/getPlaylistSongs/{playlistId}", method = RequestMethod.GET)
	public void getPlaylistSongs(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("playlistId") String playlistId) throws IOException, JSONException{			
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		long plId = Long.parseLong(playlistId);
		response.getWriter().write(playlistService.getPlaylistSongs(plId));;
		
	}
	
	@RequestMapping(value="renamePlaylist/{playlistId}", method = RequestMethod.POST)
	public void renamePlaylist(HttpServletResponse response, HttpServletRequest request
			,@PathVariable("playlistId") String playlistId) throws IOException{
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}	
		String newName = request.getParameter("playlistName");	
		if(playlistService.renamePlaylist(newName, playlistId)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
	
	@RequestMapping(value="deletePlaylist/{playlistId}", method = RequestMethod.POST)
	public void deletePlaylist(HttpServletResponse response, HttpServletRequest request
			,@PathVariable("playlistId") String playlistId) throws IOException{
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}	
		if(playlistService.removePlayList(playlistId)){

			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
}
