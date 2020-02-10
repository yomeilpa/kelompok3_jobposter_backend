package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.ApplicantEducationModel;

@Repository
@Transactional
public class AppEducationDao  extends CommonDao{
	
	@Transactional
	public ApplicantEducationModel saveAccount(ApplicantEducationModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public ApplicantEducationModel findbyid(String id) {
		List<ApplicantEducationModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From ApplicantEducationModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new ApplicantEducationModel();
		} else
			return (ApplicantEducationModel) lstCandidateModels.get(0);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ApplicantEducationModel> findcandidate(String id) {
		List<ApplicantEducationModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From ApplicantEducationModel where candidate.id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ApplicantEducationModel> findcandidateandEduation(String id,String is) {
		List<ApplicantEducationModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From ApplicantEducationModel where candidate.id=:id and education.id=:is").setParameter("is", is).setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}

	@Transactional
	public void deleteCandidate(String id) {
		ApplicantEducationModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<ApplicantEducationModel> findAll() {
		List<ApplicantEducationModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From ApplicantEducationModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}


}
