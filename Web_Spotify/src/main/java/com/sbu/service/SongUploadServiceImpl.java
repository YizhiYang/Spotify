package com.sbu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sbu.model.Song;
import com.sbu.repository.SongUploadRepo;

public class SongUploadServiceImpl implements SongUploadService {
	
	@Autowired
	private SongUploadRepo songUploadRepo;

	public boolean addSongToDatabase(Song song) {
		// TODO Auto-generated method stub
		return songUploadRepo.addSong(song);
	}

}
