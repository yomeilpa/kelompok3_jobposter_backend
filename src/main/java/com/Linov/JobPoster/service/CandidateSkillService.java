package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.CandidateSkillDao;
import com.Linov.JobPoster.model.CandidateSkillModel;

@Service
public class CandidateSkillService {
	
	@Autowired
	CandidateSkillDao acc;
	
	public CandidateSkillModel insertModel(CandidateSkillModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(CandidateSkillModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public CandidateSkillModel findById(String id) {
		CandidateSkillModel model = acc.findbyid(id);
		return model;
	}
	public List<CandidateSkillModel> findAll() {
		return acc.findAll();
	}
	
	public List<CandidateSkillModel> findCandidate(String id) {
		return acc.findByCandidate(id);
	}

}
