package com.Linov.JobPoster.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.Linov.JobPoster.model.JobSaldoModel;

@Repository
@Transactional
public class JobSaldoDao extends CommonDao {
	
	@Transactional
	public JobSaldoModel saveAccount(JobSaldoModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public JobSaldoModel findbyid(String id) {
		List<JobSaldoModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobSaldoModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobSaldoModel();
		} else
			return (JobSaldoModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		JobSaldoModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobSaldoModel> findAll() {
		List<JobSaldoModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From WorkExperienceHeader").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}



}
