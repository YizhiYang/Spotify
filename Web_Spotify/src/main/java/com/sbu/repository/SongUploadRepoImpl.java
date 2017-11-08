package com.sbu.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.Song;

@Repository("songUploadRepo")
@Transactional
public class SongUploadRepoImpl implements SongUploadRepo {
	
	@PersistenceContext
	private EntityManager em;


	public boolean addSong(Song song) {
		// TODO Auto-generated method stub
		em.persist(song);
		return true;
	}

}
