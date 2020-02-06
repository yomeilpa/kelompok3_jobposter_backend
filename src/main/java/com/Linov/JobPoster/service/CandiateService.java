package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.CandidateDao;
import com.Linov.JobPoster.model.CandidateModel;

@Service("candidateService")
public class CandiateService {

	@Autowired
	CandidateDao candidate;
	

	public CandidateModel findById(String id) {
		CandidateModel model = candidate.findbyid(id);
		return model;
	}

	public List<CandidateModel> findByname(String id) {
		return candidate.findbyname(id);
	}

	public List<CandidateModel> findAll() {
		return candidate.findAll();
	}

	public CandidateModel insertModel(CandidateModel model) {
		return candidate.saveCandidate(model);
		
	}

	public CandidateModel updateModel(CandidateModel model) {
		return candidate.saveCandidate(model);
		
	}

	public void deleteModel(String id) {
		candidate.deleteCandidate(id);
	}
	
	public List<CandidateModel> valid(CandidateModel candidate) {
		return this.candidate.validasi(candidate);
	}
}
