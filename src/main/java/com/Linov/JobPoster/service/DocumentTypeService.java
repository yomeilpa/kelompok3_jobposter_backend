package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.DocumentTypeDao;
import com.Linov.JobPoster.model.DocumentTypeModel;

@Service
public class DocumentTypeService {

	@Autowired
	DocumentTypeDao acc;
	
	public DocumentTypeModel insertModel(DocumentTypeModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(DocumentTypeModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public DocumentTypeModel findById(String id) {
		DocumentTypeModel model = acc.findbyid(id);
		return model;
	}
	public List<DocumentTypeModel> findAll() {
		return acc.findAll();
	}
	
	public List<DocumentTypeModel> findTrue() {
		return acc.findTrue();
	}
}
