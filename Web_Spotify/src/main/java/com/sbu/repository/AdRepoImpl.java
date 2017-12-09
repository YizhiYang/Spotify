package com.sbu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.model.Advertisement;

@Repository("adsRepo")
@Transactional
public class AdRepoImpl implements AdRepo {
	
	@PersistenceContext
	private EntityManager em;

	public void addAds(Advertisement ads) {
		
		em.persist(ads);
	}

	public void removeAds(Advertisement ads) {
		Session ses = em.unwrap(Session.class);
		ses.delete(ads);
	}
	
	public List<Advertisement> getAllAds(){
		System.out.println("Hellooooooooooooo");
		Session ses = em.unwrap(Session.class);
		List<Advertisement> list = ses.createQuery("FROM Advertisement").list();
		return list;
	}

}
