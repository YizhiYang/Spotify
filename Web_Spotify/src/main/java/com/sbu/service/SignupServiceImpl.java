package com.sbu.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.User;
import com.sbu.repository.SignupRepo;

@Service("signupService")
public class SignupServiceImpl implements SignupService {
	
	@Autowired
	private SignupRepo signupRepo;
	
	public User signupUser(User user, HttpServletRequest request) {
		signupRepo.signup(user);
		
		//create folder under profileImage then copy user-default.png to that dir. 
//		String path = request.getSession().getServletContext().getContextPath();
		
		String path = request.getContextPath();
		System.out.println(path);
		
		File file = new File(path);
		if(!file.exists()){
			System.out.println("false, make path");
			System.out.println(file.mkdirs());
		}
		return null;
	}
	
	public boolean validateUsername(String username){
		
		List result = signupRepo.validateUsername(username);	
		return result.isEmpty();
	}

}
