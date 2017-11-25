package com.sbu.repository;

public interface ProfileRepo {
	
	boolean changeProfileInfo(String email, String location, String username);
}
