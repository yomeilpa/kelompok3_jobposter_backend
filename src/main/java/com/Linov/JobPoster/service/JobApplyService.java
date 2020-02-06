package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.JobApplyDao;
import com.Linov.JobPoster.model.JobApplyModel;

@Service
public class JobApplyService {
	
	@Autowired
	JobApplyDao acc;
	
	public JobApplyModel insertModel(JobApplyModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(JobApplyModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public JobApplyModel findById(String id) {
		JobApplyModel model = acc.findbyid(id);
		return model;
	}
	public List<JobApplyModel> findAll() {
		return acc.findAll();
	}
	
	public List<JobApplyModel> findyear(String year) {
		return acc.findYear(year);
	}
	
	
	public List<JobApplyModel> findByJob(String id) {
		return acc.findByJobid(id);
	}
	
	public List<JobApplyModel> findBAcc() {
		return acc.findBAcc();
	}
	
	public List<JobApplyModel> findbyCandidate(String id) {
		return acc.findByCandidateId(id);
	}
	
	public JobApplyModel findAppCd(String id,String cd) {
		return acc.CandidateApplierd(id, cd);
	}
	
	public Long countAcc(String id) {
		return acc.countDocTypeTrue(id);
	}
	
	public Long countCandidate(String id) {
		return acc.countCandidate(id);
	}
	
	
	
	

}
