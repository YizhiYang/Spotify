package com.sbu.repository;

import java.util.List;

import com.sbu.model.Advertisement;

public interface AdRepo {
	
	void addAds(Advertisement ads);
	
	void removeAds(Advertisement ads);
	
	List<Advertisement> getAllAds();
}
