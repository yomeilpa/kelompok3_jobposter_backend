package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.JobPostingDao;
import com.Linov.JobPoster.model.FilterJobPosting;
import com.Linov.JobPoster.model.JobPostingModel;

@Service
public class JobPostingService {
	
	@Autowired
	JobPostingDao acc;
	
	public JobPostingModel insertModel(JobPostingModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(JobPostingModel model) {
		acc.saveAccount(model);
		
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public JobPostingModel findById(String id) {
		JobPostingModel model = acc.findbyid(id);
		return model;
	}
	public List<JobPostingModel> findAll() {
		return acc.findAll();
	}
	
	public List<JobPostingModel> findbyFiletr(String provId,String cityId,String title,Double max, Double min) {
		return acc.finByFilter(provId,cityId,title,max,min);
	}

}
