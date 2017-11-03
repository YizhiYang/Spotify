package com.sbu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.User;
import com.sbu.repository.SignupRepo;

@Service("signupService")
public class SignupServiceImpl implements SignupService {
	
	@Autowired
	private SignupRepo signupRepo;
	
	

	public User signupUser(User user) {
		// TODO Auto-generated method stub
		signupRepo.signup(user);
		return null;
	}

}
