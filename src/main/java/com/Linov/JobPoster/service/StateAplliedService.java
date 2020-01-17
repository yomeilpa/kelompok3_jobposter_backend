package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.StateAppliedDao;
import com.Linov.JobPoster.model.State_AppliedModel;

@Service
public class StateAplliedService {
	
	
	@Autowired
	StateAppliedDao acc;
	
	public State_AppliedModel insertModel(State_AppliedModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(State_AppliedModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public State_AppliedModel findById(String id) {
		State_AppliedModel model = acc.findbyid(id);
		return model;
	}
	public List<State_AppliedModel> findAll() {
		return acc.findAll();
	}
	
	
}
