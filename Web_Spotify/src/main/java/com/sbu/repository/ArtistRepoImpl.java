package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.ArtistUser;

@Repository("artistRepo")
@Transactional
public class ArtistRepoImpl implements ArtistRepo {

	
	@PersistenceContext
	private EntityManager em;
	
	public List<ArtistUser> getAllArtists() {
		List<ArtistUser> results = em.createQuery("FROM ArtistUser").getResultList();
		return results;
	}
}
