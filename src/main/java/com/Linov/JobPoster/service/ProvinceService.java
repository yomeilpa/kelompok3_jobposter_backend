package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.ProvinceDao;
import com.Linov.JobPoster.model.ProvinceModel;

@Service
public class ProvinceService {
	
	@Autowired
	private ProvinceDao prov;
	
	public ProvinceModel saveProv(ProvinceModel province) {
		return prov.saveProvince(province);
	}

	public ProvinceModel findByprovince(String name) {
		return prov.findbyprovince(name);
	}
	
	public List<ProvinceModel> find(){
		return prov.find();
	}
}
