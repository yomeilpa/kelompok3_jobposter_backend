package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.CityModel;

@Repository("City")
@Transactional
public class CityDao extends CommonDao {

	public CityModel saveCity(CityModel city) {
		return super.entityManager.merge(city);
	}

	@SuppressWarnings("unchecked")
	public CityModel findbyid(String id) {
		List<CityModel> list = super.entityManager.createQuery("From CityModel where id=:id").setParameter("id", id)
				.getResultList();
		if (list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	public void deleteCity(String id) {
		CityModel city = findbyid(id);
		super.entityManager.remove(city);
	}

	@SuppressWarnings("unchecked")
	public List<CityModel> findbyProvince(String name) {
		List<CityModel> list = super.entityManager.createQuery("From CityModel where province.province =:name")
				.setParameter("name", name).getResultList();
		return list;
	}
}
