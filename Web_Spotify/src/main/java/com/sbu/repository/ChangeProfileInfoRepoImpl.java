package com.sbu.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("changProfileInfoRepo")
@Transactional
public class ChangeProfileInfoRepoImpl implements ChangeProfileInfoRepo {

	public boolean changeProfileInfo(String email, String location) {
		
		return false;
	}

}
