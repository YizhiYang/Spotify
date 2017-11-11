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
		
		long artistId = Long.valueOf(id);
		
		List result = em.createQuery("SELECT e FROM ArtistUser e WHERE e.artistID= :id")
		.setParameter("id", artistId).getResultList();
		
		System.out.println(result.isEmpty());
		return result;
	}
	
}
