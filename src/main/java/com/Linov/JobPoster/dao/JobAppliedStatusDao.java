package com.Linov.JobPoster.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.Linov.JobPoster.model.JobAppliedStatus;

@Repository
@Transactional
public class JobAppliedStatusDao extends CommonDao {

	@Transactional
	public JobAppliedStatus saveAccount(JobAppliedStatus account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public JobAppliedStatus findbyid(String id) {
		List<JobAppliedStatus> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobAppliedStatus where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobAppliedStatus();
		} else
			return (JobAppliedStatus) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		JobAppliedStatus candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobAppliedStatus> findAll() {
		List<JobAppliedStatus> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobAppliedStatus").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}

}
