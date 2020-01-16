package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.AppEducationDao;
import com.Linov.JobPoster.model.ApplicantEducationModel;

@Service
public class AppEducationService {
	
	@Autowired
	AppEducationDao acc;
	
	public ApplicantEducationModel insertModel(ApplicantEducationModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(ApplicantEducationModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public ApplicantEducationModel findById(String id) {
		ApplicantEducationModel model = acc.findbyid(id);
		return model;
	}
	public List<ApplicantEducationModel> findAll() {
		return acc.findAll();
	}

}
