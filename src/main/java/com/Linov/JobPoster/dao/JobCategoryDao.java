package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.JobKategoriModel;

@Repository
@Transactional
public class JobCategoryDao extends CommonDao{
	
	@Transactional
	public JobKategoriModel saveAccount(JobKategoriModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public JobKategoriModel findbyid(String id) {
		List<JobKategoriModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobKategoriModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobKategoriModel();
		} else
			return (JobKategoriModel) lstCandidateModels.get(0);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public JobKategoriModel findbyCode(String id) {
		List<JobKategoriModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobKategoriModel where code=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobKategoriModel();
		} else
			return (JobKategoriModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		JobKategoriModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobKategoriModel> findAll() {
		List<JobKategoriModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobKategoriModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}

}
