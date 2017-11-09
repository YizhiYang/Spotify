package com.sbu.repository;

import java.util.List;

import com.sbu.model.User;

public interface LoginRepo {

	List Login(User user);
	List getUserAllInformation(String username);
}
