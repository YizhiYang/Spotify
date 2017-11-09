package com.sbu.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbu.repository.ChangeProfileInfoRepo;

import com.sbu.controller.MainController;


@Service("changProfileInfoService")
public class ChangProfileInfoServiceImpl implements ChangeProfileInfoService {
	
	
	@Autowired
	private ChangeProfileInfoRepo changProfileInfoRepo;
	
	public boolean changeUserProfile(String location, String email, String username) {
		return changProfileInfoRepo.changeProfileInfo(email, location, username);
	}

	public boolean changeUserProfileImage(MultipartFile file, String username) throws IOException {
		String profileFolderName = username;
		System.out.println(profileFolderName);

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File fileToWriteTo = new File(classloader.getResource(MainController.PROFILE_IMAGE_PATH+profileFolderName).getPath(), MainController.PROFILE_IMAGE_NAME);
		
		
		BufferedOutputStream outputStream = new BufferedOutputStream(
	               new FileOutputStream(fileToWriteTo));
		
	         outputStream.write(file.getBytes());
	         outputStream.flush();
	         outputStream.close();
		return true;
	}

}
