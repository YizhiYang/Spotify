package com.sbu.repository;

import java.util.List;

import com.sbu.model.CreditCard;
import com.sbu.model.User;

public interface SignupRepo {
	
		User signup(User user);
		
		List validateUsername(String username);
		
		List getUserByID(String id);
		
		boolean saveUserToDB(User user);

		void storeCreditCard(CreditCard creditCard);
		
		List<User> searchByUsername(String username);
}
