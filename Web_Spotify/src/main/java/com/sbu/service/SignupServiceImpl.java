package com.sbu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.User;
import com.sbu.repository.SignupRepo;

@Service("signupService")
public class SignupServiceImpl implements SignupService {
	
	@Autowired
	private SignupRepo signupRepo;
	
	

	public User signupUser(User user) {
		
		signupRepo.signup(user);
		return null;
	}
	
	public boolean validateUsername(String username){
		
		List result = signupRepo.validateUsername(username);
		
		return result.isEmpty();
	}

}
