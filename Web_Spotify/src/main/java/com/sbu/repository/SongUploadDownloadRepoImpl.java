package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.Song;

@Repository("songUploadRepo")
@Transactional
public class SongUploadDownloadRepoImpl implements SongUploadDownloadRepo {
	
	@PersistenceContext
	private EntityManager em;


	public boolean addSong(Song song) {
		// TODO Auto-generated method stub
		em.persist(song);
		return true;
	}


	public String findSongFileNameBasedOnIDInDatabase(long id) {
		// TODO Auto-generated method stub
		List results = em.createQuery("SELECT s.fileName FROM Song s WHERE s.songId = :id").setParameter("id", id).getResultList();
		if(results.isEmpty()){
			return null;
		}else{
			return results.get(0).toString();
		}
	}


	public List<Song> getAllSongs() {
		// TODO Auto-generated method stub
		List<Song> results = em.createQuery("FROM Song").getResultList();
		return results;
	}

}
