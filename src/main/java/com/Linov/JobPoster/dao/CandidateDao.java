package com.Linov.JobPoster.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.CandidateModel;

@Repository("candidate")
@Transactional
public class CandidateDao extends CommonDao {

	public CandidateModel saveCandidate(CandidateModel candidate) {
		return super.entityManager.merge(candidate);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public CandidateModel findbyid(String id) {
		List<CandidateModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new CandidateModel();
		} else
			return (CandidateModel) lstCandidateModels.get(0);
	}
	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateModel> findbyname(String name) {
		List<CandidateModel> lstCandidateModels = super.entityManager
				.createQuery("From CandidateModel where lower(name) like:name")
				.setParameter("name","%"+name.toLowerCase()+"%").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateModel> validasi(CandidateModel candidate) {
		List<CandidateModel> lstCandidateModels = super.entityManager
				.createQuery("From CandidateModel where lower(email) =:name or  lower(phone) =:phone")
				.setParameter("name",candidate.getEmail().toLowerCase())
				.setParameter("phone", candidate.getPhone().toLowerCase()).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new ArrayList<CandidateModel>();
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	public void deleteCandidate(String id) {
		CandidateModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateModel> findAll() {
		List<CandidateModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateModel> findbyfilter() {
		List<CandidateModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}
	
	
}
