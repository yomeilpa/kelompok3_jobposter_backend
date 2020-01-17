package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.AccountDao;
import com.Linov.JobPoster.model.UserModel;

@Service
public class AccountService {
	
	@Autowired
	AccountDao acc;
	
	public void insertModel(UserModel model) {
		acc.saveAccount(model);
	}

	public void updateModel(UserModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public UserModel findById(String id) {
		UserModel model = acc.findbyid(id);
		return model;
	}
	public List<UserModel> findAll() {
		return acc.findAll();
	}
	
	public UserModel findbyEmail(String id) {
		UserModel model = acc.findusername(id);
		return model;
	}
}
