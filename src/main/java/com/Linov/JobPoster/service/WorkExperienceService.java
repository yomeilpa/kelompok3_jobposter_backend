package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.WorkExperienceDao;
import com.Linov.JobPoster.model.WorkExperienceHeader;

@Service
public class WorkExperienceService {
	
	

	@Autowired
	WorkExperienceDao acc;
	
	public WorkExperienceHeader insertModel(WorkExperienceHeader model) {
		return acc.saveAccount(model);
	}

	public void updateModel(WorkExperienceHeader model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public WorkExperienceHeader findById(String id) {
		WorkExperienceHeader model = acc.findbyid(id);
		return model;
	}
	public List<WorkExperienceHeader> findAll() {
		return acc.findAll();
	}

}
