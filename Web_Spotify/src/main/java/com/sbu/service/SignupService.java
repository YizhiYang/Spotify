package com.sbu.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.sbu.model.CreditCard;
import com.sbu.model.User;

public interface SignupService{

		User signupUser(User user, HttpServletRequest request) throws IOException;
		
		boolean validateUsername(String username);
		
		User upgradeUser(User user, HttpServletRequest request);
		
		User downgradeUser(User user);
		
		void storeCreditCard(CreditCard creditCard);
		
		String getFriendsInJSON(User user) throws JSONException;
		
		boolean addFriendToList(User user, String friendUsername);
		
		void removeFriendToList(User user, String friendUsername);
		
		User getUserByID(String id);
}
