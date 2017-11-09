package com.sbu.controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sbu.model.Song;
import com.sbu.model.User;
import com.sbu.service.ChangeProfileInfoService;
import com.sbu.service.LoginService;
import com.sbu.service.SignupService;
import com.sbu.service.SongUploadDownloadService;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MainController {
	
	public static final String PROFILE_IMAGE_PATH = "ProfileImages/";
	public static final String PROFILE_IMAGE_NAME = "profile.png";
	
	
	//SONG FILE PATH
	public static final String SONG_FILE_PATH = "Songs/";
	public static final String REQUEST_SUCCESS = "success";
	public static final String REQUEST_FAILURE = "failure";
	
	
	public static final String FILE_NOT_FOUND_MESSAGE = "Sorry. The file you are looking for does not exist";
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SignupService signupService;
	
	@Autowired
	private SongUploadDownloadService songUploadService;
	
	@Autowired
	private ChangeProfileInfoService changeProfileInfoService;
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
		
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User tempUser = new User();
		tempUser.setUserName(username);
		tempUser.setPassword(password);
		
		if(loginService.loginUser(tempUser)) {
			
			//If login success, return basic user info to user. and add user to session
			User user = this.initNewUser(username);
			request.getSession().setAttribute("User", user);
			response.getWriter().write(REQUEST_SUCCESS);
		}else {
			response.getWriter().write(REQUEST_FAILURE);
		}		
	}
	
	public User initNewUser(String username) {

		User user = loginService.initUser(username);
		return user;
	}
	
	
	@RequestMapping(value = "/goToHome")
	public String goToHomepage(Model model, HttpServletRequest request) {
		//VALIDATE SESSION;
		User user = (User)request.getSession().getAttribute("User");
		if(user != null){
			return "Homepage";
		}else{
			return "../../index";
		}
	}
	
	
	@RequestMapping(value = "/goToProfile")
	public String goToProfile(Model model, HttpServletRequest request) {
		//VALIDATE SESSION;
		User user = (User)request.getSession().getAttribute("User");
		if(user != null){
			System.out.println(user.getUserName());
			return "userProfile";
		}else{
			return "../../index";
		}
	}
	
	@RequestMapping(value="/validateUsername", method = RequestMethod.GET)
	public void validateUsername(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String username = request.getParameter("username");	
		if(signupService.validateUsername(username)) {
			String greetings = "true";
			response.setContentType("text/plain");
			response.getWriter().write(greetings);
		}else {
			String greetings = "false";
			response.setContentType("text/plain");
			response.getWriter().write(greetings);
		}		
	}
	
	@RequestMapping(value="/searchSongs", method = RequestMethod.GET)
	public void searchSongs(HttpServletRequest request, HttpServletResponse response){
		
	}
		
	
	
	
	@RequestMapping(value = "/signup")
	public void Signup(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
        String email = request.getParameter("email");
        String username = request.getParameter("username");
		String password = request.getParameter("password");
		String location = request.getParameter("location");
		
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setLocation(location);
		
		boolean result = signupService.validateUsername(username);
		
		if(!result){
			String greetings = "false";
			response.setContentType("text/plain");
			response.getWriter().write(greetings);
		}
		else{
			signupService.signupUser(user, request);
			String greetings = "true";
			response.setContentType("text/plain");
			response.getWriter().write(greetings);
		}
    }
	
//	
//	public void sendImage(HttpServletRequest request, HttpServletResponse response){
//		
//	}
	
	
	@RequestMapping(value = "/passwordController", method=RequestMethod.POST)
	public String authenticate(Model model, HttpServletRequest request){
		System.out.println(request.getParameter("username"));
		LOGGER.info(request.getParameter("password"));
		model.addAttribute("testing", "Hello I am Matt");
		return "hello";
	}
	

	
	// Handling Profile Image upload request
	   @PostMapping(value = "/ProfileImageUpload")
	   public ResponseEntity<Object> imageUpload(@RequestParam("fileUp") MultipartFile file, HttpServletRequest request)
	         throws IOException {
		   System.out.println("lalaalala");
		   User user = (User)request.getSession().getAttribute("User");
			if(user==null){
				return null;
			}
			
			changeProfileInfoService.changeUserProfileImage(file, user.getUserName());
			return new ResponseEntity<Object>(REQUEST_SUCCESS,HttpStatus.OK);
	   }
	
	
	@RequestMapping(value="/Profile-Image", method = RequestMethod.GET)
    public void downloadProfileImage(HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		User user = (User)request.getSession().getAttribute("User");
		
		if(user==null){
			System.out.println("Kicked out of Session");
			return;
		}
		
		String profileFolderName = user.getUserName();
        File file = null;

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        
        System.out.println(classloader.getResource(PROFILE_IMAGE_PATH+profileFolderName).getPath());
        
        file = new File(classloader.getResource(PROFILE_IMAGE_PATH+profileFolderName+"/"+PROFILE_IMAGE_NAME).getFile());
        if(!file.exists()){
            String errorMessage = FILE_NOT_FOUND_MESSAGE;
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
         
        System.out.println("mimetype : "+mimeType);
         
        response.setContentType(mimeType);
         
        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
 
         
        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int)file.length());
        

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
 
        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
	
	
	
	
	
	
	
	
	@RequestMapping(value="/getAllSongs", method = RequestMethod.GET)
	public void getAllSongs(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		List<Song> list = songUploadService.getALLSongs();
		for(int i=0; i<list.size();i++){
			System.out.println(list.get(i).getFileName());
		}

		response.setContentType("text/plain");
		Gson gson = new Gson();
		String jsonString = gson.toJson(list);
		System.out.println(jsonString);
	    response.getWriter().write(jsonString);
	}
	
	
		@PostMapping(value = "/SongFileUpload")
	   public void songUpload(MultipartHttpServletRequest request, HttpServletResponse response)
	         throws IOException {
		   System.out.println("lalaalala");
		   User user = (User)request.getSession().getAttribute("User");
			if(user==null){
				return;
			}
			String songName = request.getParameter("songName");
			String artistName = request.getParameter("artistName");
			String albumName = request.getParameter("albumName");
			String duration = request.getParameter("duration");
			MultipartFile file = request.getFile("fileUp");
			Song song = new Song();
			song.setSong_name(songName);
			song.setDuration(duration);
			song.setFileName(file.getOriginalFilename());
			System.out.println(song.getSongId());

			if(songUploadService.addSongToDatabase(song)){
				System.out.println(song.getSongId());
				
				//NOW SAVE THE MUSIC FILE
				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
				File newFile = new File(classloader.getResource(SONG_FILE_PATH).getPath(),song.getFileName());		
				BufferedOutputStream outputStream = new BufferedOutputStream(
			               new FileOutputStream(newFile));
				
			         outputStream.write(file.getBytes());
			         outputStream.flush();
			         outputStream.close();
			         
			    
			    response.setContentType("text/plain");
			    response.getWriter().write(REQUEST_SUCCESS);
			}else{
				response.setContentType("text/plain");
			    response.getWriter().write(REQUEST_FAILURE);
			}
			
			
			/*
			Iterator<String> itr =  request.getFileNames();
			 
		     MultipartFile mpf = request.getFile(itr.next());
		     System.out.println(mpf.getOriginalFilename() +" uploaded!");
		     */
			
			//System.out.println(file.getOriginalFilename());
			/*
			String profileFolderName = user.getUserName();
			System.out.println(profileFolderName);

			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			File fileToWriteTo = new File(classloader.getResource(PROFILE_IMAGE_PATH+profileFolderName).getPath(),PROFILE_IMAGE_NAME);
			
			
			BufferedOutputStream outputStream = new BufferedOutputStream(
		               new FileOutputStream(fileToWriteTo));
			
		         outputStream.write(file.getBytes());
		         outputStream.flush();
		         outputStream.close();
		         
			System.out.println(file.getSize());

			System.out.println(file.getBytes());
			*/
	      
	      //return new ResponseEntity<Object>("File Uploaded Successfully.",HttpStatus.OK);
	   }
	
	
	
	@RequestMapping(value="/requestSongFile/{id}", method = RequestMethod.GET)
    public void downloadSongFile(HttpServletResponse response, HttpServletRequest request, @PathVariable("id") String id) throws IOException {
		
		User user = (User)request.getSession().getAttribute("User");
		
		if(user==null){
			System.out.println("Kicked out of Session");
			return;
		}
		
		System.out.println(id);

        File file = null;

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        
        String songFileName = null;
        
        songFileName = songUploadService.findSongFileBasedOnID(Long.valueOf(id));
        if(songFileName == null){
        	return;
        }
        /*
        if(Integer.valueOf(id) == 0){
        	songFileName = "Activ-Doar Cu Tine.mp3";
        }else if(Integer.valueOf(id) == 1){
        	songFileName = "Jos - Crosses.mp3";
        }
        */
        
        
        String songPath = classloader.getResource(SONG_FILE_PATH).getPath();
        System.out.println(classloader.getResource(SONG_FILE_PATH).getPath());
        System.out.println(songPath+songFileName);
        
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
         
        System.out.println("mimetype : "+mimeType);
         
        response.setContentType(mimeType);
        
         
        // "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
        //   while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
 
         
        // "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int)file.length());
        
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
        
        
        
    }
	
	
	@RequestMapping(value = "/getUserProfile", method = RequestMethod.GET)
	public void getUserProfile(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			System.out.println("Kicked out of Session");
			return;
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(user);
	    response.getWriter().write(jsonString);
	}
	
	
	
	/*
	 * Called when user changes account info such as email and location.
	 * */
	
	@RequestMapping(value = "/changeUserProfileInfo", method = RequestMethod.POST)
	public void changeUserProfileInfo(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		User user = (User)request.getSession().getAttribute("User");
		
		if(user==null){
			System.out.println("Kicked out of Session");
			return;
		}
		
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		
		boolean changeSuccess = changeProfileInfoService.changeUserProfile(location, email, user.getUserName());
		if(changeSuccess){
			user.setEmail(email);
			user.setLocation(location);
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
		
	}
 
}
