package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.CityDao;
import com.Linov.JobPoster.model.CityModel;

@Service
public class CityService {
	
	@Autowired
	CityDao city;
	
	public CityModel save(CityModel citys) {
		return city.saveCity(citys);
		
	}
	
	public List<CityModel> citis(String prov){
		return city.findbyProvince(prov);
	}
	
	public CityModel findbyid(String id) {
		return city.findbyid(id);
	}

}
