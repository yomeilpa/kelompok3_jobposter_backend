package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.JobDetailDao;
import com.Linov.JobPoster.model.JobDetailModel;

@Service
public class JobDetailService {
	
	@Autowired
	JobDetailDao acc;
	
	public JobDetailModel insertModel(JobDetailModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(JobDetailModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public JobDetailModel findById(String id) {
		JobDetailModel model = acc.findbyid(id);
		return model;
	}
	public List<JobDetailModel> findAll() {
		return acc.findAll();
	}
	
	public List<JobDetailModel> findbyJobP(String id) {
		return acc.findbyIdjobPosting(id);
	}
	
	public void delete(String id) {
		acc.deleteJb(id);
	}


}
