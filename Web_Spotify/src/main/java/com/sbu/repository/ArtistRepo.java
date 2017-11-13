package com.sbu.repository;

import java.util.List;

import com.sbu.model.ArtistUser;

public interface ArtistRepo {
	List<ArtistUser> getAllArtists();
	List<ArtistUser> getArtistByArtistID(String id);
}
