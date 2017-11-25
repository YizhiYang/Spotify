package com.sbu.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbu.model.CreditCard;
import com.sbu.model.User;

public interface SignupService{

		User signupUser(User user, HttpServletRequest request) throws IOException;
		
		boolean validateUsername(String username);
		
		void upgradeUser(User user, HttpServletRequest request);
		
		void storeCreditCard(CreditCard creditCard);
		
}
