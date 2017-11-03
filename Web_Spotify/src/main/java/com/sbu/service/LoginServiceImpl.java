package com.sbu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.User;
import com.sbu.repository.LoginRepo;


// put business here. not in the repository
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepo loginRepo;
	public boolean loginUser(User user) {
		List result = loginRepo.Login(user);
		return !result.isEmpty();
		
	}

}
