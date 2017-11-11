package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;

@Repository("GenericValidationRepo")
@Transactional
public class GenericValidationRepoImpl implements GenericValidationRepo {
	
	
	@PersistenceContext
	private EntityManager em;

	public List checkArtistExist(String id) {
		
		long artistId = Long.valueOf(id);
		
		System.out.println("Artist ID is: " + artistId);
		
		List result = em.createQuery("SELECT e FROM ArtistUser e WHERE e.artistID = :id")
		.setParameter("id", artistId).getResultList();
		
		System.out.println("Size is:" + result.size());
		return result;
	}

	public boolean saveAlbumToDB(Album album) {
		// TODO Auto-generated method stub
		em.persist(album);
		return true;
	}

	public boolean saveAristToDB(ArtistUser artist) {
		em.persist(artist);
		return true;
	}
	
}
