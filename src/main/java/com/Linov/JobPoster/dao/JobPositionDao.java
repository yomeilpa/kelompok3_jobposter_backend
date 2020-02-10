package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.Linov.JobPoster.model.JobPosition;

@Repository
@Transactional
public class JobPositionDao extends CommonDao {

	@Transactional
	public JobPosition saveAccount(JobPosition account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public JobPosition findbyid(String id) {
		List<JobPosition> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPosition where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobPosition();
		} else
			return (JobPosition) lstCandidateModels.get(0);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public JobPosition findbyCode(String id) {
		List<JobPosition> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPosition where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobPosition();
		} else
			return (JobPosition) lstCandidateModels.get(0);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobPosition> findKategoribyid(String id) {
		List<JobPosition> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPosition where jobkategori.id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	

	@Transactional
	public void deleteCandidate(String id) {
		JobPosition candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobPosition> findAll() {
		List<JobPosition> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPosition").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
}
