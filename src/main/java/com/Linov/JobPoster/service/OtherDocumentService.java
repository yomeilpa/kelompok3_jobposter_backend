package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.OtherDocumentDao;
import com.Linov.JobPoster.model.OtherDocumentModel;

@Service
public class OtherDocumentService {
	
	@Autowired
	OtherDocumentDao acc;
	
	public OtherDocumentModel insertModel(OtherDocumentModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(OtherDocumentModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public OtherDocumentModel findById(String id) {
		OtherDocumentModel model = acc.findbyid(id);
		return model;
	}
	public List<OtherDocumentModel> findAll() {
		return acc.findAll();
	}

}
