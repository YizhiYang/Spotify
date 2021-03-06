package com.sbu.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import com.sbu.model.User;

public interface ProfileService {
	
	boolean changeUserProfile(String location, String email, String username);
	
	boolean changeUserProfileImage(MultipartFile file, String username) throws IOException;
	
	String getUserInfoInJSON(User user) throws JSONException;
}
