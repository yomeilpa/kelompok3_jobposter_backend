package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.CandidateSalaryExpectedDao;
import com.Linov.JobPoster.model.CandidateSalaryExpectedModel;

@Service
public class CandidateSalaryExpectedService {

	@Autowired
	CandidateSalaryExpectedDao acc;
	
	public CandidateSalaryExpectedModel insertModel(CandidateSalaryExpectedModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(CandidateSalaryExpectedModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public CandidateSalaryExpectedModel findById(String id) {
		CandidateSalaryExpectedModel model = acc.findbyid(id);
		return model;
	}
	public List<CandidateSalaryExpectedModel> findAll() {
		return acc.findAll();
	}
}
