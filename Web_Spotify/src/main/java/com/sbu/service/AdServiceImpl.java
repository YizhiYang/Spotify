package com.sbu.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.model.Advertisement;
import com.sbu.model.Playlist;
import com.sbu.repository.AdRepo;
import com.sbu.repository.AlbumRepo;

@Service("adService")
public class AdServiceImpl implements AdService {
	
	@Autowired
	private AdRepo adRepo;

	public void addAds(Advertisement ads) {
		adRepo.addAds(ads);
	}

	public void removeAds(Advertisement ads) {
		adRepo.removeAds(ads);
	}
	
	public List<Advertisement> getAllAds(){
		return adRepo.getAllAds();
	}
	
	public String convertAdsToJSON(List<Advertisement> ads) throws JSONException {
		JSONArray jsonArray = new JSONArray();	
		for(int i =0; i<ads.size(); i++){
			
			Advertisement ad = ads.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("Id", ad.getId());
			jsonObject.put("ImageURL", ad.getImageUrl());		
			jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
	}
}
