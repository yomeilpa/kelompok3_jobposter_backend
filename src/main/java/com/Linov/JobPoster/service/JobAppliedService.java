package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.JobAppliedStatusDao;
import com.Linov.JobPoster.model.JobAppliedStatus;

@Service
public class JobAppliedService {
	
	@Autowired
	JobAppliedStatusDao acc;
	
	public JobAppliedStatus insertModel(JobAppliedStatus model) {
		return acc.saveAccount(model);
	}

	public void updateModel(JobAppliedStatus model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public JobAppliedStatus findById(String id) {
		JobAppliedStatus model = acc.findbyid(id);
		return model;
	}
	public List<JobAppliedStatus> findAll() {
		return acc.findAll();
	}

}
