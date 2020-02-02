package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.Linov.JobPoster.model.JobApplyModel;

@Repository
@Transactional
public class JobApplyDao  extends CommonDao{
	
	@Transactional
	public JobApplyModel saveAccount(JobApplyModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public JobApplyModel findbyid(String id) {
		List<JobApplyModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobApplyModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobApplyModel();
		} else
			return (JobApplyModel) lstCandidateModels.get(0);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public JobApplyModel CandidateApplierd(String id,String cd) {
		List<JobApplyModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobApplyModel where job.id=:id and candidate.id=:cd").setParameter("cd", cd).setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			JobApplyModel sk = new JobApplyModel();
			sk.setId("x");
			return sk;
		} else
			return (JobApplyModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		JobApplyModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobApplyModel> findAll() {
		List<JobApplyModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobApplyModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobApplyModel> findByCandidateId(String id) {
		List<JobApplyModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobApplyModel where candidate.id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	public Long countDocTypeTrue(String id) {
		Long lstCandidateModels =  (Long) super.entityManager
				.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id and state.state = 'Accepted'").setParameter("id", id).getSingleResult();	
			return lstCandidateModels;
	}
	
	public Long countCandidate(String id) {
		Long lstCandidateModels =  (Long) super.entityManager
				.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id").setParameter("id", id).getSingleResult();	
			return lstCandidateModels;
	}
	

}	
