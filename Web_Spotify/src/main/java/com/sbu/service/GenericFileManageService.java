package com.sbu.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.User;

public interface GenericFileManageService {
	
	public void saveFileToLocation(MultipartFile file, String path, String filename) throws IOException;
	
	public void createPicInProfileImages(MultipartFile file, String id) throws IOException;
	
}
