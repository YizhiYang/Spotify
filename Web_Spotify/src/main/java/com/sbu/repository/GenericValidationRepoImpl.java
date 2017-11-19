package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.Album;
import com.sbu.model.ArtistUser;
import com.sbu.model.User;

@Repository("GenericValidationRepo")
@Transactional
public class GenericValidationRepoImpl implements GenericValidationRepo {
	
	
	@PersistenceContext
	private EntityManager em;

	public List checkArtistExist(String id) {
		
		long artistId = Long.valueOf(id);
		
		List result = em.createQuery("SELECT u FROM ArtistUser u WHERE u.artistID = :id")
		.setParameter("id", artistId).getResultList();
		return result;
	}

	public boolean saveAlbumToDB(Album album) {
		em.persist(album);
		return true;
	}
	
	public List getUserByID(String id){
		long userId = Long.valueOf(id);
		
		List result = em.createQuery("SELECT u FROM User u WHERE u.id = :id")
		.setParameter("id", userId).getResultList();
		return result;
	}

	public boolean saveAristToDB(ArtistUser artist) {
		em.persist(artist);
		return true;
	}

	public boolean saveUserToDB(User user) {
		em.persist(user);
		return true;
	}
	
}
