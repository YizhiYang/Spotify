package com.sbu.controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionContext;

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
import org.springframework.web.servlet.ModelAndView;

import com.sbu.model.User;
import com.sbu.service.LoginService;
import com.sbu.service.SignupService;

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
import java.util.logging.Logger;

@Controller
public class MainController {
	
	public static final String PROFILE_IMAGE_PATH = "ProfileImages/";
	public static final String PROFILE_IMAGE_NAME = "profile.png";
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SignupService signupService;
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
		
	@RequestMapping(value = "/testing", method = RequestMethod.POST)
	public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		
		request.getSession().setAttribute("User", user);
		if(loginService.loginUser(user)) {
			String greetings = "true";
			response.setContentType("text/plain");
			response.getWriter().write(greetings);
		}else {
			String greetings = "false";
			response.setContentType("text/plain");
			response.getWriter().write(greetings);
		}		
	}
	
	
	@RequestMapping(value = "/goToHome")
	public String goToHomepage(Model model, HttpServletRequest request) {
		//VALIDATE SESSION;
		User user = (User)request.getSession().getAttribute("User");
		if(user != null){
			System.out.println(user.getUserName());
			return "Homepage";
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
	

	
	// Handling file upload request
	   @PostMapping(value = "/ProfileImageUpload")
	   public ResponseEntity<Object> imageUpload(@RequestParam("fileUp") MultipartFile file, HttpServletRequest request)
	         throws IOException {
		   System.out.println("lalaalala");
		   User user = (User)request.getSession().getAttribute("User");
			if(user==null){
				return null;
			}
			
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
			
	      
	      return new ResponseEntity<Object>("File Uploaded Successfully.",HttpStatus.OK);
	   }
	
	
	@RequestMapping(value="/Profile-Image", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		User user = (User)request.getSession().getAttribute("User");
		if(user==null){
			return;
		}
		
		String profileFolderName = user.getUserName();
		
     
        File file = null;

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        
        file = new File(classloader.getResource(PROFILE_IMAGE_PATH+profileFolderName+"/"+PROFILE_IMAGE_NAME).getFile());
         
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
         
        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
 
         
        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int)file.length());
        

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        //byte[] imageBytes = new byte[(int)file.length()];
        //inputStream.read(imageBytes, 0, imageBytes.length);
        //inputStream.close();
        //String imageStr = Base64.getEncoder().encodeToString(imageBytes);
 
        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
 
}
