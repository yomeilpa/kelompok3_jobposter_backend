package com.Linov.JobPoster.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.ProvinceModel;

@Repository
@Transactional
public class ProvinceDao extends CommonDao {
	
	public ProvinceModel saveProvince(ProvinceModel province) {
		return super.entityManager.merge(province);
	}
	
	public ProvinceModel findbyprovince(String name) {
		return (ProvinceModel) super.entityManager.createQuery("From ProvinceModel where code =:pro").setParameter("pro", name).getResultList().get(0);
	}

}
