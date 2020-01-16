package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.EducationModel;

@Repository("Education")
@Transactional
public class EducationDao extends CommonDao {
	
	@Transactional
	public void saveAccount(EducationModel account) {
		super.entityManager.merge(account);
		
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public EducationModel findbyid(String id) {
		List<EducationModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From EducationModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new EducationModel();
		} else
			return (EducationModel) lstCandidateModels.get(0);
	}
	
	@Transactional
	public void deleteCandidate(String id) {
		EducationModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<EducationModel> findAll() {
		List<EducationModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From EducationModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}

}
