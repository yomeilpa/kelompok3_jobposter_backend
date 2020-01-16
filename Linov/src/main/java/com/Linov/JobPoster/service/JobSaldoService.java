package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Linov.JobPoster.dao.JobSaldoDao;
import com.Linov.JobPoster.model.JobSaldoModel;

@Service
public class JobSaldoService {
	
	@Autowired
	JobSaldoDao acc;
	
	public JobSaldoModel insertModel(JobSaldoModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(JobSaldoModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public JobSaldoModel findById(String id) {
		JobSaldoModel model = acc.findbyid(id);
		return model;
	}
	public List<JobSaldoModel> findAll() {
		return acc.findAll();
	}

}
