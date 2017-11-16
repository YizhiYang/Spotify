package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.Song;

@Repository("songRepo")
@Transactional
public class SongRepoImpl implements SongRepo {
	
	@PersistenceContext
	private EntityManager em;


	public boolean addSong(Song song) {
		em.persist(song);
		return true;
	}


	public List<Song> getAllSongs() {
		List<Song> results = em.createQuery("FROM Song").getResultList();
		return results;
	}


	public List<Song> getSearchSongResults(String searchString) {
		List<Song> results = em.createQuery("SELECT s FROM Song s WHERE s.songName LIKE :searchString")
				.setParameter("searchString", "%"+searchString+"%").getResultList();
		return results;
	}
}
