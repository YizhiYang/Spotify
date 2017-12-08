package com.sbu.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.User;
import com.sbu.service.AlbumService;
import com.sbu.service.ArtistService;
import com.sbu.service.GenericFileManageService;
import com.sbu.service.SongService;

@Controller
public class AlbumsController {
	
	
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
	private GenericFileManageService fileManager;
	
	
	@RequestMapping(value="/getAllAlbums", method = RequestMethod.GET)
	public void getAllAlbums(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		String jsonString = albumService.getAllAlbumsInJSON();
 		response.setContentType("text/plain");		
	    response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value="/loadOwnedAlbums", method = RequestMethod.GET)
	public void loadOwnedAlbums(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		String albumIds = albumService.getOwnedAlbumIds(user);
 		response.setContentType("text/plain");		
	    response.getWriter().write(albumIds);
	}
	
	@RequestMapping(value="/requestAlbumImage/{id}", method = RequestMethod.GET)
    public void requestAlbumImage(HttpServletResponse response, HttpServletRequest request, @PathVariable("id") String id) throws IOException {
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			System.out.println("Kicked out of Session");
			return;
		}
		
        File file = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();    
        String songFileName = null;    
        songFileName = id + ALBUMS_EXTENSION;
        if(songFileName == null){
        	return;
        } 
        String songPath = classloader.getResource(ALBUMS_FILE_PATH).getPath();      
        file = new File(songPath+songFileName);
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
         
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }       
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());
        
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
	
	
	@PostMapping(value = "/UploadAlbum")
	public void uploadAlbum(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
			
		String albumName = request.getParameter("albumName");
		String id = request.getParameter("artistID");
		MultipartFile pic = request.getFile("fileUp");
		
		ArtistUser returnedArtist = artistService.checkArtistExist(id);
		if(returnedArtist == null){
			response.getWriter().write(REQUEST_FAILURE);
		}
		else{
			Album album = new Album();
			album.setAlbum_name(albumName);
			albumService.saveAlbum(album);
			
			returnedArtist.getAlbum().add(album);
			artistService.saveArtist(returnedArtist);
			fileManager.createPicInProfileImages(pic, album.getAlbumId().toString());
		}
		
		response.getWriter().write(REQUEST_SUCCESS);
	}
	
	
	@RequestMapping(value = "/removeAlbum/{albumID}", method = RequestMethod.POST)
	public void removeAlbum(HttpServletResponse response, HttpServletRequest request,@PathVariable("albumID") String albumID)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}	
		albumService.removeAlbum(albumID);
	    response.getWriter().write(REQUEST_SUCCESS);
	}
	
	
	@RequestMapping(value = "/getAllSongsInAlbum/{id}", method = RequestMethod.GET)
	public void getAllSongsInAlbum(HttpServletResponse response, HttpServletRequest request,@PathVariable("id") String id)
			throws JSONException, IOException {
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		Album album = albumService.getAlbumByID(id);
		String jsonString = songService.convertSongsToJSON(album.getSongs());	
	    response.getWriter().write(jsonString);
	}
}
