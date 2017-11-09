package com.sbu.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ChangeProfileInfoService {
	
	boolean changeUserProfile(String location, String email, String username);
	boolean changeUserProfileImage(MultipartFile file, String username) throws IOException;
}
