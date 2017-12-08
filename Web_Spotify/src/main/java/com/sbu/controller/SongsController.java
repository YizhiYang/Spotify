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
import com.sbu.model.Song;
import com.sbu.model.User;
import com.sbu.service.AlbumService;
import com.sbu.service.GenericFileManageService;
import com.sbu.service.SongService;

@Controller
public class SongsController {
	
	public static final String SONG_FILE_PATH = "Songs/";
	public static final String SONG_EXTENSION = ".mp3";
	public static final String LYRICS_EXTENSION = ".lrc";
	public static final String REQUEST_SUCCESS = "success";
	public static final String REQUEST_FAILURE = "failure";
	public static final String FILE_NOT_FOUND_MESSAGE = "Sorry. The file you are looking for does not exist";
	
	@Autowired
	private SongService songService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private GenericFileManageService fileManager;
	
	
	@RequestMapping(value = "/removeSong/{songId}", method = RequestMethod.POST)
	public void removeSong(HttpServletResponse response, HttpServletRequest request, 
			@PathVariable("songId") String songId) throws IOException{
		
		songService.removeSong(songId);
		response.getWriter().write(REQUEST_SUCCESS);
	}
	
	
	@PostMapping(value = "/SongFileUpload")
	public void songUpload(MultipartHttpServletRequest request, HttpServletResponse response)
			throws IOException {

		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		String songName = request.getParameter("songName");
		String albumID = request.getParameter("albumID");
		String duration = request.getParameter("duration");
		MultipartFile file = request.getFile("fileUp");
		MultipartFile lyricsFile = request.getFile("lyricsFileUp");
		
		Album albumToSaveTo = albumService.getAlbumByID(albumID);
		if(albumToSaveTo==null){
			return;
		}
		Song song = new Song();
		song.setSongName(songName);
		song.setDuration(duration);
		song.setAlbum(albumToSaveTo);

		if(songService.addSongToDatabase(song)){
			fileManager.saveFileToLocation(file, SONG_FILE_PATH, song.getSongId()+SONG_EXTENSION);
			if(lyricsFile!=null){
				fileManager.saveFileToLocation(lyricsFile, SONG_FILE_PATH, song.getSongId()+LYRICS_EXTENSION);
			}
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
	
	
	@RequestMapping(value="/requestSongFile/{id}", method = RequestMethod.GET)
    public void downloadSongFile(HttpServletResponse response, HttpServletRequest request, @PathVariable("id") String id) throws IOException {
			
        File file = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();    
        String songFileName = null;
        
        songFileName = id + SONG_EXTENSION;
        
        String songPath = classloader.getResource(SONG_FILE_PATH).getPath();
      
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
	
	@RequestMapping(value="/getAllSongs", method = RequestMethod.GET)
	public void getAllSongs(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		String jsonString = songService.getAllSongsInJSON();
	    response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value="/getAllSongsPendingApproval", method = RequestMethod.GET)
	public void getAllSongsPendingApproval(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		String jsonString = songService.getAllPendingSongsInJSON();
	    response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value = "/approveSong/{songId}", method = RequestMethod.POST)
	public void approveSong(HttpServletResponse response, HttpServletRequest request,@PathVariable("songId") String songId)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		songService.approveSong(songId);
		
		response.getWriter().write(REQUEST_SUCCESS);
	}
	
	@RequestMapping(value="/requestLyricsFile/{id}", method = RequestMethod.GET)
    public void requestLyricsFile(HttpServletResponse response, HttpServletRequest request, @PathVariable("id") String id) throws IOException {
		File file = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();    
        String lyricsFileName = null;
        
        lyricsFileName = id + LYRICS_EXTENSION;
        
        String songPath = classloader.getResource(SONG_FILE_PATH).getPath();
      
        file = new File(songPath+lyricsFileName);
        if(!file.exists()){
        	response.getWriter().write("not found");
        }else{
        	response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
            response.setContentLength((int)file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
	}
}
