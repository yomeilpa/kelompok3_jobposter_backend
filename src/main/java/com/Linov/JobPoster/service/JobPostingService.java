package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.JobPostingDao;
import com.Linov.JobPoster.model.FilterJobPosting;
import com.Linov.JobPoster.model.JobPostingModel;
import com.Linov.JobPoster.model.ReportPerYear;

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
	
	public List<JobPostingModel> findAllbyPopster(String id) {
		return acc.findBycandidateid(id);
	}
	public List<JobPostingModel> findbyFiletr(FilterJobPosting fg) {
		return acc.finByFilter(fg);
	}
	
	public void updateState() {
		acc.updateState();
	}
	
	public List<ReportPerYear> oks(){
		return acc.findforReport();
	}

}
