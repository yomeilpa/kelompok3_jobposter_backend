package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.CandidateHiredDao;
import com.Linov.JobPoster.model.CandidateHired;

@Service
public class CandidateHiredService {
	
	@Autowired
	CandidateHiredDao acc;
	
	public CandidateHired insertModel(CandidateHired model) {
		return acc.saveAccount(model);
	}

	public void updateModel(CandidateHired model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public CandidateHired findById(String id) {
		CandidateHired model = acc.findbyid(id);
		return model;
	}
	public List<CandidateHired> findAll() {
		return acc.findAll();
	}


}
