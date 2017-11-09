package com.sbu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.repository.ChangeProfileInfoRepo;

@Service("changProfileInfoService")
public class ChangProfileInfoServiceImpl implements ChangeProfileInfoService {
	
	
	@Autowired
	private ChangeProfileInfoRepo changProfileInfoRepo;
	
	public boolean changeUserProfile(String location, String email) {
		
		return false;
	}

}
