package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.AccountDao;
import com.Linov.JobPoster.model.UserModel;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AccountService implements UserDetailsService {
	
	@Autowired
	AccountDao acc;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = acc.findusername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		} 
		String password = bcryptEncoder.encode(user.getPassword());
		return new org.springframework.security.core.userdetails.User(user.getCandidate().getEmail(), password, new ArrayList<>());
	}
	
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
	
	public Long countCand() {
		return acc.countCandidate();
	}
	
	
	
	
}
