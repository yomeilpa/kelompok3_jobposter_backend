package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.CandidateSkillModel;

@Repository
@Transactional
public class CandidateSkillDao extends CommonDao {
	
	@Transactional
	public CandidateSkillModel saveAccount(CandidateSkillModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public CandidateSkillModel findbyid(String id) {
		List<CandidateSkillModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateSkillModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new CandidateSkillModel();
		} else
			return (CandidateSkillModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		CandidateSkillModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateSkillModel> findAll() {
		List<CandidateSkillModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateSkillModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateSkillModel> findByCandidate(String id) {
		List<CandidateSkillModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateSkillModel where candidate.id=:id").setParameter("id",id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}



}
