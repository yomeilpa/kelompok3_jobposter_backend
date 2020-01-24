package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.CandidateDocument;

@Repository
@Transactional
public class OtherDocumentDao extends CommonDao {

	@Transactional
	public CandidateDocument saveAccount(CandidateDocument account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public CandidateDocument findbyid(String id) {
		List<CandidateDocument> lstCandidateModels = super.entityManager
				.createQuery("" + "From OtherDocumentModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new CandidateDocument();
		} else
			return (CandidateDocument) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		CandidateDocument candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateDocument> findAll() {
		List<CandidateDocument> lstCandidateModels = super.entityManager
				.createQuery("" + "From OtherDocumentModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateDocument> findTrue() {
		List<CandidateDocument> lstCandidateModels = super.entityManager
				.createQuery("" + "From OtherDocumentModel where doctype.flag = true").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
}
