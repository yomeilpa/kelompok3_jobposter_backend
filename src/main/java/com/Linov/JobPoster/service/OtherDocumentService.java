package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.OtherDocumentDao;
import com.Linov.JobPoster.model.CandidateDocument;

@Service
public class OtherDocumentService {
	
	@Autowired
	OtherDocumentDao acc;
	
	public CandidateDocument insertModel(CandidateDocument model) {
		return acc.saveAccount(model);
	}

	public void updateModel(CandidateDocument model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public CandidateDocument findById(String id) {
		CandidateDocument model = acc.findbyid(id);
		return model;
	}
	public List<CandidateDocument> findAll() {
		return acc.findAll();
	}
	
	public CandidateDocument findTrue(String id,String as) {
		return acc.findbyIdDocType(id, as);
	}
	
	
	

}
