package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.JobPositionDao;
import com.Linov.JobPoster.model.JobPosition;

@Service
public class JobPositionService {
	
	@Autowired
	JobPositionDao acc;
	
	public JobPosition insertModel(JobPosition model) {
		return acc.saveAccount(model);
	}

	public void updateModel(JobPosition model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public JobPosition findById(String id) {
		JobPosition model = acc.findbyid(id);
		return model;
	}
	
	public List<JobPosition> findAll() {
		return acc.findAll();
	}
	
	public List<JobPosition> findbyKate(String id) {
		return acc.findKategoribyid(id);
	}
}
