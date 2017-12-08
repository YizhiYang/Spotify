package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.Song;
import com.sbu.model.User;

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
	
	public List<Song> getSongByID(String songId) {
		long songIdLong = Long.valueOf(songId);
		List<Song> results = em.createQuery("SELECT s FROM Song s WHERE s.songId= :songId")
				.setParameter("songId", songIdLong).getResultList();
		return results;
	}

	public List<Song> getSearchSongResults(String searchString) {
		List<Song> results = em.createQuery("SELECT s FROM Song s WHERE s.songName LIKE :searchString")
				.setParameter("searchString", "%"+searchString+"%").getResultList();
		return results;
	}


	public List<User> getAllFollowers(String songId) {
		List<User> results = em.createQuery("SELECT u FROM User u JOIN u.followedSongs s WHERE s.songId= :songId")
				.setParameter("songId", Long.valueOf(songId)).getResultList();
		return results;
	}


	public void removeSong(String songId) {
		em.createQuery("DELETE From Song s WHERE s.songId = :songId")
		.setParameter("songId", Long.valueOf(songId)).executeUpdate();
	}


	public List<User> getAllUsersWhoPlayedSong(String songId) {
		List<User> results = em.createQuery("SELECT u FROM User u JOIN u.playedSongsHistory s WHERE s.songId= :songId")
				.setParameter("songId", Long.valueOf(songId)).getResultList();
		return results;
	}
	
}
