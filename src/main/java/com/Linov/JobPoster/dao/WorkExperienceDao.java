package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.WorkExperienceHeader;

@Repository
@Transactional
public class WorkExperienceDao extends CommonDao {

	@Transactional
	public WorkExperienceHeader saveAccount(WorkExperienceHeader account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public WorkExperienceHeader findbyid(String id) {
		List<WorkExperienceHeader> lstCandidateModels = super.entityManager
				.createQuery("" + "From WorkExperienceHeader where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new WorkExperienceHeader();
		} else
			return (WorkExperienceHeader) lstCandidateModels.get(0);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<WorkExperienceHeader> findCan(String id) {
		List<WorkExperienceHeader> lstCandidateModels = super.entityManager
				.createQuery("" + "From WorkExperienceHeader where candidate.id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}

	@Transactional
	public void deleteCandidate(String id) {
		WorkExperienceHeader candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<WorkExperienceHeader> findAll() {
		List<WorkExperienceHeader> lstCandidateModels = super.entityManager
				.createQuery("" + "From WorkExperienceHeader").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}

}
