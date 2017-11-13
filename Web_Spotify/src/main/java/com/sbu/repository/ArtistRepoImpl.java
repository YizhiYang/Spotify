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

	public List<ArtistUser> getArtistByArtistID(String id) {
		long artistId = Long.valueOf(id);
		
		List<ArtistUser> result = em.createQuery("SELECT a FROM ArtistUser a WHERE a.artistID = :id")
		.setParameter("id", artistId).getResultList();
		return result;
	}
}
