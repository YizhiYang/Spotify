package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.Album;


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

}
