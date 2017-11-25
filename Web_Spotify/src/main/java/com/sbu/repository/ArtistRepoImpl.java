package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.ArtistUser;
import com.sbu.model.User;

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
		
		List<ArtistUser> result = em.createQuery("SELECT "
				+ "a FROM ArtistUser a WHERE a.artistID = :id")
		.setParameter("id", artistId).getResultList();
		return result;
	}

	public List<ArtistUser> getSearchArtistResults(String searchString) {
		List<ArtistUser> result = em.createQuery("SELECT "
				+ "a FROM ArtistUser a WHERE a.artistName LIKE :searchString")
		.setParameter("searchString", "%"+searchString+"%").getResultList();
		return result;
	}

	public List<ArtistUser> getArtistsOfAlbum(long albumId) {
		List<ArtistUser> result = em.createQuery("SELECT "
				+ "a FROM ArtistUser a JOIN a.album al WHERE al.albumId = :albumId")
		.setParameter("albumId", albumId).getResultList();
		return result;
	}
	

	public boolean saveAristToDB(ArtistUser artist) {
		em.persist(artist);
		return true;
	}
	
	public List checkArtistExist(String id) {
		
		long artistId = Long.valueOf(id);
		
		List result = em.createQuery("SELECT u FROM ArtistUser u WHERE u.artistID = :id")
		.setParameter("id", artistId).getResultList();
		return result;
	}

	public List<User> getAllFollowers(String artistId) {
		List<User> results = em.createQuery("SELECT "
				+ "u FROM User u JOIN u.followedArtists a WHERE a.artistID= :artistId")
				.setParameter("artistId", Long.valueOf(artistId)).getResultList();
		return results;
	}

	public void removeArtist(String artistId) {
		em.createQuery("DELETE From ArtistUser a WHERE a.artistID = :artistId")
		.setParameter("artistId", Long.valueOf(artistId)).executeUpdate();
	}
}
