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
import com.sbu.service.ArtistService;
import com.sbu.service.ContentFollowService;

@Controller
public class FollowController {
	
	public static final String REQUEST_SUCCESS = "success";
	public static final String REQUEST_FAILURE = "failure";
	
	@Autowired
	private ContentFollowService contentFollowService;
	@Autowired
	private ArtistService artistService;
	
	@RequestMapping(value = "/addToFollowedSongs/{songId}", method = RequestMethod.POST)
	public void addToFollowedSongs(HttpServletResponse response, HttpServletRequest request,@PathVariable("songId") String songId)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		if(contentFollowService.addToUserFollowedSongs(user, songId)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
	
	@RequestMapping(value = "/removeFromFollowedSongs/{songId}", method = RequestMethod.POST)
	public void removeFromFollowedSongs(HttpServletResponse response, HttpServletRequest request,@PathVariable("songId") String songId)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		if(contentFollowService.removeFromFollowedSongs(user, songId)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
	
	
	@RequestMapping(value = "/loadFollowedSongs", method = RequestMethod.GET)
	public void loadFollowedSongs(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		String jsonString = contentFollowService.getFollowedSongsInJSON(user);
		response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value = "/addToFollowedAlbums/{albumId}", method = RequestMethod.POST)
	public void addToFollowedAlbums(HttpServletResponse response, HttpServletRequest request,@PathVariable("albumId") String albumId)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		if(contentFollowService.addToUserFollowedAlbums(user, albumId)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
	
	@RequestMapping(value = "/removeFromFollowedAlbums/{albumId}", method = RequestMethod.POST)
	public void removeFromFollowedAlbums(HttpServletResponse response, HttpServletRequest request,@PathVariable("albumId") String albumId)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		if(contentFollowService.removeFromFollowedAlbums(user, albumId)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
	
	@RequestMapping(value = "/removeFromFollowedArtists/{artistId}", method = RequestMethod.POST)
	public void removeFromFollowedArtists(HttpServletResponse response, HttpServletRequest request,@PathVariable("artistId") String artistId)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		if(contentFollowService.removeFromFollowedArtists(user, artistId)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
	
	@RequestMapping(value = "/loadFollowedAlbums", method = RequestMethod.GET)
	public void loadFollowedAlbums(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		String jsonString = contentFollowService.getFollowedAlbumsInJSON(user);	
		response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value = "/addToFollowedArtists/{artistId}", method = RequestMethod.POST)
	public void addToFollowedArtists(HttpServletResponse response, HttpServletRequest request,@PathVariable("artistId") String artistId)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		if(contentFollowService.addToUserFollowedArtists(user, artistId)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
	
	@RequestMapping(value = "/removeArtist/{artistID}", method = RequestMethod.POST)
	public void removeArtist(HttpServletResponse response, HttpServletRequest request,@PathVariable("artistID") String artistID)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}	
		artistService.removeArtist(artistID);
	    response.getWriter().write(REQUEST_SUCCESS);
	}
	
	@RequestMapping(value = "/loadFollowedArtists", method = RequestMethod.GET)
	public void loadFollowedArtists(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}	
		String jsonString = contentFollowService.getFollowedArtistsInJSON(user);	
		response.getWriter().write(jsonString);
	}
}
