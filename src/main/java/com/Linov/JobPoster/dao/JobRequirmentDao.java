	package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.Linov.JobPoster.model.JobRecruitmentModel;

@Repository
@Transactional
public class JobRequirmentDao  extends CommonDao{
	
	@Transactional
	public JobRecruitmentModel saveAccount(JobRecruitmentModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public JobRecruitmentModel findbyid(String id) {
		List<JobRecruitmentModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobRecruitmentModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobRecruitmentModel();
		} else
			return (JobRecruitmentModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		JobRecruitmentModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobRecruitmentModel> findAll() {
		List<JobRecruitmentModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobRecruitmentModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobRecruitmentModel> findbyIdjobPosting(String id) {
		List<JobRecruitmentModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobRecruitmentModel where job.id =:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}


	
	

}
