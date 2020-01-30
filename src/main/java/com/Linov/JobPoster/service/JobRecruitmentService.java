package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.JobRequirmentDao;
import com.Linov.JobPoster.model.JobRecruitmentModel;

@Service
public class JobRecruitmentService {
	
	@Autowired
	JobRequirmentDao acc;
	
	public JobRecruitmentModel insertModel(JobRecruitmentModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(JobRecruitmentModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public JobRecruitmentModel findById(String id) {
		JobRecruitmentModel model = acc.findbyid(id);
		return model;
	}
	public List<JobRecruitmentModel> findAll() {
		return acc.findAll();
	}
	
	public List<JobRecruitmentModel> findbyJobPos(String id) {
		return acc.findbyIdjobPosting(id);
	}

}
