package com.sbu.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("fileManager")
public class GenericFileManageServiceImpl implements GenericFileManageService {
	
	public static final String ALBUMS_PATH = "/AlbumImages";
	
	public void createPicInProfileImages(MultipartFile file, String id) throws IOException{
			
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File newFile = new File(classloader.getResource(ALBUMS_PATH).getPath(), id + ".jpg");		
		BufferedOutputStream outputStream = new BufferedOutputStream(
	               new FileOutputStream(newFile));
		
	         outputStream.write(file.getBytes());
	         outputStream.flush();
	         outputStream.close();
		
	}
}
