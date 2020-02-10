package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.JobCategoryDao;
import com.Linov.JobPoster.model.JobKategoriModel;

@Service
public class JobkategoriService {
	
	@Autowired
	JobCategoryDao acc;
	
	public JobKategoriModel insertModel(JobKategoriModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(JobKategoriModel model) {
		acc.saveAccount(model);
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public JobKategoriModel findById(String id) {
		JobKategoriModel model = acc.findbyid(id);
		return model;
	}
	public List<JobKategoriModel> findAll() {
		return acc.findAll();
	}
	
	public JobKategoriModel findByCode(String id) {
		JobKategoriModel model = acc.findbyCode(id);
		return model;
	}
	

}
