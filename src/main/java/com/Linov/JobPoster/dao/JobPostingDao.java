package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.JobPostingModel;

@Repository
@Transactional
public class JobPostingDao  extends CommonDao{
	
	@Transactional
	public JobPostingModel saveAccount(JobPostingModel account) {
		return super.entityManager.merge(account);	
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public JobPostingModel findbyid(String id) {
		List<JobPostingModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPostingModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobPostingModel();
		} else
			return (JobPostingModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		JobPostingModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobPostingModel> findAll() {
		List<JobPostingModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPostingModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}

}
