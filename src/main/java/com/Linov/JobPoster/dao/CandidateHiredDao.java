package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.CandidateHired;

@Repository
@Transactional
public class CandidateHiredDao extends CommonDao {
	
	
	@Transactional
	public CandidateHired saveAccount(CandidateHired account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public CandidateHired findbyid(String id) {
		List<CandidateHired> lstCandidateModels = super.entityManager
				.createQuery("" + "CandidateHired where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new CandidateHired();
		} else
			return (CandidateHired) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		CandidateHired candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateHired> findAll() {
		List<CandidateHired> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateHired").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}


}
