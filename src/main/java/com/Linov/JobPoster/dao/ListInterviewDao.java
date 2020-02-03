package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.Linov.JobPoster.model.ListofInterviewModel;

@Repository
@Transactional
public class ListInterviewDao extends CommonDao{
	
	@Transactional
	public ListofInterviewModel saveAccount(ListofInterviewModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public ListofInterviewModel findbyid(String id) {
		List<ListofInterviewModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From ListofInterviewModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new ListofInterviewModel();
		} else
			return (ListofInterviewModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		ListofInterviewModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<ListofInterviewModel> findAll() {
		List<ListofInterviewModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From ListofInterviewModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ListofInterviewModel> findAllbyPoster(String id) {
		List<ListofInterviewModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From ListofInterviewModel as where as.job.job.candidate.id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ListofInterviewModel> findInterviewCandidate(String id) {
		List<ListofInterviewModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From ListofInterviewModel where job.candidate.id =:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}
}
