package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.JobDetailModel;

@Transactional
@Repository
public class JobDetailDao extends CommonDao{

	@Transactional
	public JobDetailModel saveAccount(JobDetailModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public JobDetailModel findbyid(String id) {
		List<JobDetailModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobDetailModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobDetailModel();
		} else
			return (JobDetailModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		JobDetailModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobDetailModel> findAll() {
		List<JobDetailModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobDetailModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobDetailModel> findbyIdjobPosting(String id) {
		List<JobDetailModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobDetailModel where job.id =:id").setParameter("id", id).getResultList();
	
			return lstCandidateModels;
	}
	
	public void deleteJb(String id) {
		super.entityManager.createQuery("delete from JobDetailModel where job.id=:id").setParameter("id", id).executeUpdate();
	}



}
