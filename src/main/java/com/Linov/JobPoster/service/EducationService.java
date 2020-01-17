package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.EducationDao;
import com.Linov.JobPoster.model.EducationModel;

@Service
public class EducationService {
	
	@Autowired
	EducationDao acc;
	
	public void insertModel(EducationModel model) {
		acc.saveAccount(model);
	}

	public void updateModel(EducationModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public EducationModel findById(String id) {
		EducationModel model = acc.findbyid(id);
		return model;
	}
	public List<EducationModel> findAll() {
		return acc.findAll();
	}
}
