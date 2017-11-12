package com.sbu.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.User;

public interface GenericFileManageService {
	
	public void createPicInProfileImages(MultipartFile file, String id) throws IOException;
	
	public ArtistUser checkArtistExist(String id);
	
	public boolean saveAlbum(Album album);
	
	public boolean saveArtist(ArtistUser artist);
	
	public boolean makeNewArtist(String userID, String artistName);
}
