package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.CandidateSalaryExpectedModel;

@Repository
@Transactional
public class CandidateSalaryExpectedDao extends CommonDao{

	@Transactional
	public CandidateSalaryExpectedModel saveAccount(CandidateSalaryExpectedModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public CandidateSalaryExpectedModel findbyid(String id) {
		List<CandidateSalaryExpectedModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateSalaryExpectedModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new CandidateSalaryExpectedModel();
		} else
			return (CandidateSalaryExpectedModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		CandidateSalaryExpectedModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateSalaryExpectedModel> findAll() {
		List<CandidateSalaryExpectedModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateSalaryExpectedModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}


	
}
