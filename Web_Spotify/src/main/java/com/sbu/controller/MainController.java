package com.sbu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sbu.model.User;
import com.sbu.service.LoginService;
import com.sbu.service.SignupService;

import java.io.IOException;
import java.util.logging.Logger;

@Controller
public class MainController {
	
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
			String greetings = "true";
			response.setContentType("text/plain");
			response.getWriter().write(greetings);
		}
    }
	
	
	public void sendImage(HttpServletRequest request, HttpServletResponse response){
		
		
	}
	
	
	@RequestMapping(value = "/passwordController", method=RequestMethod.POST)
	public String authenticate(Model model, HttpServletRequest request){
		System.out.println(request.getParameter("username"));
		LOGGER.info(request.getParameter("password"));
		model.addAttribute("testing", "Hello I am Matt");
		return "hello";
	}
}
