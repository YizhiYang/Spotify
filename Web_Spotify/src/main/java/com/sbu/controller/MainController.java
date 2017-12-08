package com.sbu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.sbu.model.ArtistUser;
import com.sbu.model.CreditCard;
import com.sbu.model.User;
import com.sbu.model.UserType;
import com.sbu.service.AlbumService;
import com.sbu.service.ArtistService;
import com.sbu.service.ProfileService;
import com.sbu.service.ContentFollowService;
import com.sbu.service.GenericFileManageService;
import com.sbu.service.LoginService;
import com.sbu.service.PlaylistService;
import com.sbu.service.SignupService;
import com.sbu.service.SongService;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

@Controller
public class MainController {
	
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
	private ProfileService profileService;
	
		
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User tempUser = new User();
		tempUser.setUserName(username);
		tempUser.setPassword(password);
		
		if(loginService.loginUser(tempUser)) {
			
			User user = this.initNewUser(username);
			request.getSession().setAttribute("User", user);
			response.getWriter().write(username);
		}else {
			response.getWriter().write(REQUEST_FAILURE);
		}		
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().invalidate();
		response.getWriter().write(REQUEST_SUCCESS);
	}
	
	public User initNewUser(String username) {
		User user = loginService.initUser(username);
		return user;
	}
	

	@RequestMapping(value = "/getUserNameOnPageLoad")
	public void getUserName(HttpServletResponse response, HttpServletRequest request) throws IOException {

		User user = (User)request.getSession().getAttribute("User");
		if(user != null){
			response.getWriter().write(user.getUserName());
		}
	}
	
	
	@RequestMapping(value = "/goToHome")
	public String goToHomepage(Model model, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("User");
		if(user != null){
			return "Homepage";
		}else{
			return "../../index";
		}
	}
	
	
	@RequestMapping(value = "/goToProfile")
	public String goToProfile(Model model, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("User");
		if(user != null){
			System.out.println(user.getUserName());
			return "userProfile";
		}else{
			return "../../index";
		}
	}
	
	
	@RequestMapping(value="/validateUsername", method = RequestMethod.GET)
	public void validateUsername(Model model, HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		
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
	
	
	@RequestMapping(value = "/signup")
	public void Signup(Model model, HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		
        String email = request.getParameter("email");
        String username = request.getParameter("username");
		String password = request.getParameter("password");
		String location = request.getParameter("location");
		
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setLocation(location);
		user.setUserType(UserType.BASIC);
		
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
	
	
	   @PostMapping(value = "/ProfileImageUpload")
	   public void imageUpload(@RequestParam("fileUp") MultipartFile file, 
			   HttpServletRequest request, HttpServletResponse response)
	         throws IOException {

		   User user = (User)request.getSession().getAttribute("User");
			if(user==null){
				System.out.println("Kicked out of Session from Image Upload");
				return;
			}
			profileService.changeUserProfileImage(file, user.getUserName());
			response.getWriter().write(REQUEST_SUCCESS);
	   }
	
	
	@RequestMapping(value="/Profile-Image", method = RequestMethod.GET)
    public void getProfileImage(HttpServletResponse response, 
    		HttpServletRequest request) throws IOException {
		
		User user = (User)request.getSession().getAttribute("User");	
		if(user==null){
			System.out.println("Kicked out of Session from Image Download");
			return;
		}
		
		String profileFolderName = user.getUserName();
        File file = null;      
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        System.out.println(classloader.getResource(PROFILE_IMAGE_PATH).getPath());
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
                
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
	
			
	/**
	***********
	Search related Controller functions
	***********
	**/
	@RequestMapping(value = "/searchContent", method = RequestMethod.GET)
	public void searchContent(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		String content = request.getParameter("searchContent");
		
		User user = (User) request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		String songsJsonString = songService.searchSongs(content);
		String artistsJsonString = artistService.searchArtists(content);
		String albumsJsonString = albumService.searchAlbums(content);
		
		JSONObject JsonObject = new JSONObject();
		JsonObject.put("songsJson", songsJsonString);
		JsonObject.put("artistsJson", artistsJsonString);
		JsonObject.put("albumsJson", albumsJsonString);	
	    response.getWriter().write(JsonObject.toString());
	}
	
	
	
	/*
	 * Called when user changes account info such as email and location.
	 * */
	@RequestMapping(value = "/changeUserProfileInfo", method = RequestMethod.POST)
	public void changeProfile(HttpServletResponse response, HttpServletRequest request)
			throws JSONException, IOException {
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			System.out.println("Kicked out of Session");
			return;
		}
		
		String email = request.getParameter("email");
		String location = request.getParameter("location");		
		boolean changeSuccess = profileService.changeUserProfile(location, email, user.getUserName());
		
		if(changeSuccess){
			user.setEmail(email);
			user.setLocation(location);
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
		
	}
	
	
	@PostMapping(value = "/MakeUserArist")
	public void makeUserArtist(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		String userID = request.getParameter("userID");
		String artistName = request.getParameter("artistName");
		
		if(artistService.makeNewArtist(userID, artistName)){
			response.getWriter().write(REQUEST_SUCCESS);
		}else{
			response.getWriter().write(REQUEST_FAILURE);
		}
	}
	
	
	@RequestMapping(value="/getProfileImageWithUsername/{username}", method = RequestMethod.GET)
    public void getProfileImage(HttpServletResponse response, 
    		HttpServletRequest request, @PathVariable("username") String username) throws IOException {
		
		User user = (User)request.getSession().getAttribute("User");		
		if(user==null){
			return;
		}

		String profileFolderName = username;
        File file = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        
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
         
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
	
	@RequestMapping(value = "/checkAndAddAccountForArtists")
	public void checkAndAddAccountForArtists(Model model, HttpServletRequest request) throws IOException {
		List<ArtistUser> artists = artistService.getAllArtists();
		for(ArtistUser artist: artists){
			if(artist.getUser()==null){
				String artistname = artist.getArtistName();
				artistname = artistname.replaceAll(" ", "");
				String username = artistname;
				boolean userNameAvailable = signupService.validateUsername(username);
				int counter = 0;
				while(userNameAvailable == false){
					username = artistname + counter;
					userNameAvailable = signupService.validateUsername(username);
					counter++;
				}
				User user = new User();
				user.setUserName(username);
				user.setPassword(username);
				signupService.signupUser(user, request);
				artist.setUser(user);
				artistService.saveArtist(artist);
			}
		}
	}
	
	@RequestMapping(value="/getUserType", method = RequestMethod.GET)
	public void getUserType(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		User user = (User) request.getSession().getAttribute(("User"));
		System.out.println((user.getUserType().toString()));
		response.getWriter().write(user.getUserType().toString());
	}
	
	@RequestMapping(value="/upgradeAccount", method = RequestMethod.POST)
	public void upgradeAccount(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		User user = (User) request.getSession().getAttribute("User");
		User newUser = signupService.upgradeUser(user, request);
		request.getSession().setAttribute("User", newUser);
		response.getWriter().write(REQUEST_SUCCESS);
	}
	
	@RequestMapping(value="/downgradeAccount", method = RequestMethod.POST)
	public void downgradeAccount(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		User user = (User) request.getSession().getAttribute("User");
		request.getSession().setAttribute("User", signupService.downgradeUser(user));
		response.getWriter().write(REQUEST_SUCCESS);
	}
	
	
	@RequestMapping(value="/getFriendList", method = RequestMethod.GET)
	public void getFriendList(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
		
		User user = (User) request.getSession().getAttribute(("User"));
		
		String friendsJSON = signupService.getFriendsInJSON(user);
		
		response.getWriter().write(friendsJSON);
	}
}
