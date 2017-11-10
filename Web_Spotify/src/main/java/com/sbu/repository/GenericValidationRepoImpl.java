package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("GenericValidationRepo")
@Transactional
public class GenericValidationRepoImpl implements GenericValidationRepo {
	
	
	@PersistenceContext
	private EntityManager em;

	public List checkArtistExist(String id) {
		
		long userId = Long.valueOf(id);
		
		List result = em.createQuery("SELECT e FROM User e WHERE e.id= :userId")
		.setParameter("userId", userId).getResultList();
		
		System.out.println(result.isEmpty());

		return result;
	}
	
}
