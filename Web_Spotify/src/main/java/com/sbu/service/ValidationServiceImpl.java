package com.sbu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.repository.SongUploadDownloadRepo;
import com.sbu.repository.ValidationRepo;

@Service("validationService")
public class ValidationServiceImpl implements ValidationService {
	
	@Autowired
	private ValidationRepo validationRepo;
}
