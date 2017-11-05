package com.sbu.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="newReleasedAlbums")
public class NewReleasedAlbums {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long newReleaseAlbumId;
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	@OneToMany
	private List<Album> albums;

	public Long getNewReleaseAlbumId() {
		return newReleaseAlbumId;
	}

	public void setNewReleaseAlbumId(Long newReleaseAlbumId) {
		this.newReleaseAlbumId = newReleaseAlbumId;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	

}
