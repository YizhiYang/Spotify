package com.sbu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.Song;
import com.sbu.repository.SongUploadDownloadRepo;

@Service("songUploadService")
public class SongUploadDownloadServiceImpl implements SongUploadDownloadService {
	
	@Autowired
	private SongUploadDownloadRepo songUploadRepo;

	public boolean addSongToDatabase(Song song) {
		// TODO Auto-generated method stub
		return songUploadRepo.addSong(song);
	}

	public String findSongFileBasedOnID(Long id) {
		// TODO Auto-generated method stub
		String fileName = songUploadRepo.findSongFileNameBasedOnIDInDatabase(id);
		System.out.println(fileName);
		return fileName;
	}

	public List<Song> getALLSongs() {
		// TODO Auto-generated method stub
		return songUploadRepo.getAllSongs();
	}
	
	
	

}
