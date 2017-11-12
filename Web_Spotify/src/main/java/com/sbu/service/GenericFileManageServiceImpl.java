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
import com.sbu.repository.GenericValidationRepo;

@Service("fileManager")
public class GenericFileManageServiceImpl implements GenericFileManageService {
	
	@Autowired
	private GenericValidationRepo genericValidationRepo;
	
	
	public void createPicInProfileImages(MultipartFile file, String id) throws IOException{
			
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File newFile = new File(classloader.getResource(MainController.ALBUMS_FILE_PATH).getPath(), id + ".jpg");		
		BufferedOutputStream outputStream = new BufferedOutputStream(
	               new FileOutputStream(newFile));
		
	         outputStream.write(file.getBytes());
	         outputStream.flush();
	         outputStream.close();
		
	}
	
	
	public ArtistUser checkArtistExist(String id){
		
		return (ArtistUser)genericValidationRepo.checkArtistExist(id).get(0);
	}


	public boolean saveAlbum(Album album) {
		return genericValidationRepo.saveAlbumToDB(album);
	}


	public boolean saveArtist(ArtistUser artist) {
		return genericValidationRepo.saveAristToDB(artist);
	}


	public boolean makeNewArtist(String userID, String artistName) {
		List userResult = genericValidationRepo.getUserByID(userID);
		if(userResult.isEmpty()){
			return false;
		}else{
			ArtistUser artist = new ArtistUser();
			artist.setUser((User)(userResult.get(0)));;
			artist.setArtistName(artistName);
			genericValidationRepo.saveAristToDB(artist);
			return true;
		}
		
	}
}
