package com.sbu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@RequestMapping(value="/getAllArtists", method = RequestMethod.GET)
	public void getAllArtists(Model model, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, JSONException{
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		String jsonString = artistService.getAllArtistsInJSON();
 		response.setContentType("text/plain");
	    response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value="/loadLoggedInArtistID", method = RequestMethod.GET)
	public void loadLoggedInArtistID(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		ArtistUser artist = artistService.getArtistByUser(user);
		if(artist == null){
			response.getWriter().write("-1");
		}else{
			response.getWriter().write(artist.getArtistID()+"");
		}
	}
	
	@RequestMapping(value="/getArtistBio/{artistId}", method = RequestMethod.GET)
	public void getArtistBio(Model model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable("artistId") String artistId) throws IOException, JSONException{
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		ArtistUser artist = artistService.getArtistByArtistID(artistId);
		response.getWriter().write(artist.getBio());
	}
	
	@PostMapping(value = "/addArist")
	public void addArist(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		String imageURL = request.getParameter("imageURL");
		String artistName = request.getParameter("artistName");
		String artistBio = request.getParameter("artistBio");
		
		artistService.addArtist(imageURL, artistName, artistBio);
	}
}
