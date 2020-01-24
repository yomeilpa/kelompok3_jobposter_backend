package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.DocumentTypeModel;

@Repository
@Transactional
public class DocumentTypeDao extends CommonDao {

	@Transactional
	public DocumentTypeModel saveAccount(DocumentTypeModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public DocumentTypeModel findbyid(String id) {
		List<DocumentTypeModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From DocumentTypeModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new DocumentTypeModel();
		} else
			return (DocumentTypeModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		DocumentTypeModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<DocumentTypeModel> findAll() {
		List<DocumentTypeModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From DocumentTypeModel where flag = false").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DocumentTypeModel> findTrue() {
		List<DocumentTypeModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From DocumentTypeModel where flag = true").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
}
