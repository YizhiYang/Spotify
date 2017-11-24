package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.Album;
import com.sbu.model.User;


@Repository("albumRepo")
@Transactional
public class AlbumRepoImpl implements AlbumRepo {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Album> getAllAlbums() {
		List<Album> results = em.createQuery("FROM Album").getResultList();
		return results;
	}

	public List<Album> getAlbumByID(String id) {
		long albumId = Long.valueOf(id);
		
		List<Album> result = em.createQuery("SELECT a FROM Album a WHERE a.albumId = :id")
		.setParameter("id", albumId).getResultList();
		return result;
	}

	public List<Album> getSearchAlbumResults(String searchString) {
		List<Album> result = em.createQuery("SELECT a FROM Album a WHERE a.albumName LIKE :searchString")
		.setParameter("searchString", "%"+searchString+"%").getResultList();
		return result;
	}

	public void removeAlbum(Long albumId) {
		em.createQuery("DELETE From Album a WHERE a.albumId = :albumId")
		.setParameter("albumId", albumId).executeUpdate();
	}
	
	public boolean saveAlbumToDB(Album album) {
		em.persist(album);
		return true;
	}

	public List<User> getAllFollowers(String albumId) {
		List<User> results = em.createQuery("SELECT u FROM User u JOIN u.followedAlbums a WHERE a.albumId= :albumId")
				.setParameter("albumId", Long.valueOf(albumId)).getResultList();
		return results;
	}

}
