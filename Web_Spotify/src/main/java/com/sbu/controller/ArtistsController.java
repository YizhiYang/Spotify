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

import com.sbu.model.ArtistUser;
import com.sbu.model.User;
import com.sbu.service.AlbumService;
import com.sbu.service.ArtistService;

@Controller
public class ArtistsController {
	
	
	@Autowired
	private ArtistService artistService;
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping(value = "/getAllAlbumsInArtist/{id}", method = RequestMethod.GET)
	public void getAllAlbumsInArtist(HttpServletResponse response, HttpServletRequest request,@PathVariable("id") String id)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}	
		ArtistUser artist = artistService.getArtistByArtistID(id);
		String jsonString = albumService.convertAlbumsToJSON(artist.getAlbum());
	    response.getWriter().write(jsonString);
	}
}
