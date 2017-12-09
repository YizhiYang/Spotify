package com.sbu.repository;

import java.util.List;

import com.sbu.model.Advertisement;

public interface AdRepo {
	
	void addAds(Advertisement ads);
	
	void removeAds(String adsId);
	
	List<Advertisement> getAllAds();
}
