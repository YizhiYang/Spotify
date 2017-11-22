package com.sbu.repository;

import java.util.List;

import com.sbu.model.User;

public interface SignupRepo {
		User signup(User user);
		List validateUsername(String username);
		public List getUserByID(String id);
		public boolean saveUserToDB(User user);
}
