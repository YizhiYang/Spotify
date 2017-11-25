package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("changProfileInfoRepo")
@Transactional
public class ProfileRepoImpl implements ProfileRepo {
	
	@PersistenceContext
	private EntityManager em;

	public boolean changeProfileInfo(String email, String location, String username) {
		int success = em.createQuery("UPDATE User "
				+ "set email= :email, location= :location WHERE username= :username")
		.setParameter("email", email)
		.setParameter("location", location)
		.setParameter("username", username).executeUpdate();
		
		System.out.println("update is " + success);

		return true;
	}

}
