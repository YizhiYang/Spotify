package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.CreditCard;
import com.sbu.model.User;

@Repository("signupRepo")
@Transactional
public class SignupRepoImpl implements SignupRepo {
	
	@PersistenceContext
	private EntityManager em;

	public User signup(User user) {
		
		em.persist(user);
		return user;
	}

	public List validateUsername(String username) {
		
		List results = em.createQuery("SELECT e FROM User e WHERE e.userName LIKE :uname").setParameter("uname", username).getResultList();
		
		return results;
		
	}
	
	public List getUserByID(String id){
		long userId = Long.valueOf(id);
		
		List result = em.createQuery("SELECT u FROM User u WHERE u.id = :id")
		.setParameter("id", userId).getResultList();
		return result;
	}
	
	public boolean saveUserToDB(User user) {
		em.persist(user);
		return true;
	}

	public void storeCreditCard(CreditCard creditCard) {
		
		em.persist(creditCard);
		
	}

}
