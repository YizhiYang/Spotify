package com.sbu.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.User;

@Repository("signupRepo")
@Transactional
public class SignupRepoImpl implements SignupRepo {
	
	@PersistenceContext
	private EntityManager em;

	public User signup(User user) {
		// TODO Auto-generated method stub
		em.persist(user);
		
		
		return user;
	}

}
