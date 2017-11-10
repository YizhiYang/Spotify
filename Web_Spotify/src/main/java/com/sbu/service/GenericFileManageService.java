package com.sbu.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface GenericFileManageService {
	
	public void createPicInProfileImages(MultipartFile file, String id) throws IOException;
}
