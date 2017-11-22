package com.sbu.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbu.controller.MainController;
import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.User;

@Service("fileManager")
public class GenericFileManageServiceImpl implements GenericFileManageService {
	
	
	public void createPicInProfileImages(MultipartFile file, String id) throws IOException{
			
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File newFile = new File(classloader.getResource(MainController.ALBUMS_FILE_PATH).getPath(), id + ".jpg");		
		BufferedOutputStream outputStream = new BufferedOutputStream(
	               new FileOutputStream(newFile));
		
	         outputStream.write(file.getBytes());
	         outputStream.flush();
	         outputStream.close();
		
	}
	
	public void saveFileToLocation(MultipartFile file, String path, String filename) throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File newFile = new File(classloader.getResource(path).getPath(), filename);		
		BufferedOutputStream outputStream = new BufferedOutputStream(
	               new FileOutputStream(newFile));
		
	         outputStream.write(file.getBytes());
	         outputStream.flush();
	         outputStream.close();
		
	}




}
