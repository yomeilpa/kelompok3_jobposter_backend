package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.OtherDocumentModel;

@Repository
@Transactional
public class OtherDocumentDao extends CommonDao {

	@Transactional
	public OtherDocumentModel saveAccount(OtherDocumentModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public OtherDocumentModel findbyid(String id) {
		List<OtherDocumentModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From OtherDocumentModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new OtherDocumentModel();
		} else
			return (OtherDocumentModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		OtherDocumentModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<OtherDocumentModel> findAll() {
		List<OtherDocumentModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From OtherDocumentModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}
	
}
