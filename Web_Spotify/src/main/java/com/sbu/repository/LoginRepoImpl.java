package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.sbu.model.User;


@Repository("loginRepo")
@Transactional
public class LoginRepoImpl implements LoginRepo {

	@PersistenceContext
	private EntityManager em;

	public List Login(User user) {	
		List results = em.createQuery("SELECT e FROM User e WHERE e.userName LIKE :uname AND e.password LIKE :upassword")
				.setParameter("uname", user.getUserName())
				.setParameter("upassword", user.getPassword())
				.getResultList();
		
		return results;
	}
	
	public List getUserAllInformation(String username){
		
		List result = em.createQuery("SELECT e FROM User e WHERE e.userName LIKE :uname").setParameter("uname", username).getResultList();
		
		return result;
	}

}
