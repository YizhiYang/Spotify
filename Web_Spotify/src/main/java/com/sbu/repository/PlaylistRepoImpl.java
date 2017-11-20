package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.Playlist;
import com.sbu.model.User;

@Repository("playlistRepo")
@Transactional
public class PlaylistRepoImpl implements PlaylistRepo {
	@PersistenceContext
	private EntityManager em;

	public boolean saveNewPlaylist(Playlist playlist) {
		em.persist(playlist);
		return true;
	}

	public List<Playlist> getUserPlaylists(User user) {
		List<Playlist> playlists = em.createQuery("SELECT p FROM Playlist p WHERE p.owner = :user")
				.setParameter("user", user).getResultList();
		return playlists;
	}

	
	public List<Playlist> getPlaylistById(Long plId){
		
		List<Playlist> playlists = em.createQuery("SELECT p FROM Playlist p WHERE p.id = :plId")
				.setParameter("plId", plId).getResultList();

		return playlists;
	}

	public boolean renamePlaylist(Playlist pl) {
		em.persist(pl);
		return true;
	
	}

	public void removePlaylist(Long plId) {
	
		em.createQuery("DELETE From Playlist p WHERE p.id = :plId")
		.setParameter("plId", plId).executeUpdate();
		
	}
	


}
