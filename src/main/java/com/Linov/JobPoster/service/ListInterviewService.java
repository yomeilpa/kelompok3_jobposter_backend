package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.ListInterviewDao;
import com.Linov.JobPoster.model.ListofInterviewModel;

@Service
public class ListInterviewService {
	
	@Autowired
	ListInterviewDao acc;
	
	public ListofInterviewModel insertModel(ListofInterviewModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(ListofInterviewModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public ListofInterviewModel findById(String id) {
		return acc.findbyid(id);
	
	}
	
	
	
	public List<ListofInterviewModel> findAll() {
		return acc.findAll();
	}
	
	public List<ListofInterviewModel> findIntCd(String id) {
		return acc.findInterviewCandidate(id);
	}


}
