package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.InterviewStatusModel;

@Repository
@Transactional
public class InterviewStatusDao extends CommonDao {

	@Transactional
	public InterviewStatusModel saveAccount(InterviewStatusModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public InterviewStatusModel findbyid(String id) {
		List<InterviewStatusModel> lstCandidateModels = super.entityManager
				.createQuery("" + "FROM InterviewStatusModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new InterviewStatusModel();
		} else
			return (InterviewStatusModel) lstCandidateModels.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public InterviewStatusModel findbyname(String id) {
		List<InterviewStatusModel> lstCandidateModels = super.entityManager
				.createQuery("" + "FROM InterviewStatusModel where name=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new InterviewStatusModel();
		} else
			return (InterviewStatusModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		InterviewStatusModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<InterviewStatusModel> findAll() {
		List<InterviewStatusModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From InterviewStatusModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}


	
}
