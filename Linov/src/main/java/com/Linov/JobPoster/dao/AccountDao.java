package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.UserModel;

@Repository("Account")
@Transactional
public class AccountDao extends CommonDao {
	
	@Transactional
	public void saveAccount(UserModel account) {
		super.entityManager.merge(account);
		
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public UserModel findbyid(String id) {
		List<UserModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From UserModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new UserModel();
		} else
			return (UserModel) lstCandidateModels.get(0);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public UserModel findusername(String id) {
		List<UserModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From UserModel where candidate.email=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new UserModel();
		} else
			return (UserModel) lstCandidateModels.get(0);
	}
	
	
	
	@Transactional
	public void deleteCandidate(String id) {
		UserModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<UserModel> findAll() {
		List<UserModel> lstCandidateModels = super.entityManager
				.createQuery("" + "SELECT us.candidate, us.candidate.city  From UserModel us").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}
}


