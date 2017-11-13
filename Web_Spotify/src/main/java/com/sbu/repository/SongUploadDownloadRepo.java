package com.sbu.repository;

import java.util.List;

import com.sbu.model.Song;

public interface SongUploadDownloadRepo {
	boolean addSong(Song song);
	String findSongFileNameBasedOnIDInDatabase(long id);
	List<Song> getAllSongs();

}
