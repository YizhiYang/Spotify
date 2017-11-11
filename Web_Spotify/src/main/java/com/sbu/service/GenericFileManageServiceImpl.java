package com.sbu.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbu.model.ArtistUser;
import com.sbu.model.User;
import com.sbu.repository.GenericValidationRepo;

@Service("fileManager")
public class GenericFileManageServiceImpl implements GenericFileManageService {
	
	@Autowired
	private GenericValidationRepo genericValidationRepo;
	
	
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
	
	
	public ArtistUser checkArtistExist(String id){
		
		return (ArtistUser)genericValidationRepo.checkArtistExist(id).get(0);
	}
}
