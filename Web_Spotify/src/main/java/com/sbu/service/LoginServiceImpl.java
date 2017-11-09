package com.sbu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.User;
import com.sbu.repository.LoginRepo;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepo loginRepo;
	public boolean loginUser(User user) {
		List result = loginRepo.Login(user);
		System.out.println("Hello0000");
		return !result.isEmpty();	
	}
	public User initUser(String username) {

		List list = loginRepo.getUserAllInformation(username);
		return (User)list.get(0);
	}

}
