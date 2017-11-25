package com.sbu.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.User;
import com.sbu.repository.SignupRepo;
import com.sbu.controller.MainController;

@Service("signupService")
public class SignupServiceImpl implements SignupService {
	
	@Autowired
	private SignupRepo signupRepo;
	
	public User signupUser(User user, HttpServletRequest request) throws IOException {
		signupRepo.signup(user);
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		
		String parent_root = classloader.getResource(MainController.PROFILE_IMAGE_PATH).getPath();
		
		File newDir = new File(classloader.getResource(MainController.PROFILE_IMAGE_PATH).getPath(), user.getUserName());
		
		System.out.println(newDir.getAbsolutePath());
		System.out.println(newDir.mkdir());
	
		File source = new File(parent_root + MainController.PROFILE_IMAGE_NAME);
		
		FileUtils.copyFileToDirectory(source, newDir);
		
		
		System.out.println(source.exists());

		return null;
	}
	
	public boolean validateUsername(String username){
		
		List result = signupRepo.validateUsername(username);	
		return result.isEmpty();
	}

	public void upgradeUser(User user) {
		User u = (User) signupRepo.getUserByID(user.getId().toString()).get(0);
		signupRepo.saveUserToDB(u);
		
	}

}
