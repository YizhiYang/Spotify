package com.sbu.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("validationRepo")
@Transactional
public class ValidationRepoImpl implements ValidationRepo {

	public boolean checkArtistExist() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
