package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.InterviewStatusDao;
import com.Linov.JobPoster.model.InterviewStatusModel;

@Service
public class InterviewStatusService {
	
	@Autowired
	InterviewStatusDao acc;
	
	public InterviewStatusModel insertModel(InterviewStatusModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(InterviewStatusModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public InterviewStatusModel findById(String id) {
		InterviewStatusModel model = acc.findbyid(id);
		return model;
	}
	public List<InterviewStatusModel> findAll() {
		return acc.findAll();
	}


}
