package com.sbu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbu.model.User;

public interface SignupService {

		User signupUser(User user, HttpServletRequest request);	
		public boolean validateUsername(String username);
}
