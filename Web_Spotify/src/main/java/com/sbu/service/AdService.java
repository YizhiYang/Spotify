package com.sbu.service;

import java.util.List;

import org.json.JSONException;

import com.sbu.model.Advertisement;

public interface AdService {
	
	void addAds(Advertisement ads);
	
	void removeAds(String id);
	
	List<Advertisement> getAllAds();
	
	String convertAdsToJSON(List<Advertisement> ads) throws JSONException;
}
