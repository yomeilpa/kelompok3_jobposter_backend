package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.CandidateDocument;
import com.Linov.JobPoster.model.CandidateModel;

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
				.createQuery("" + "From CandidateDocument where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new CandidateDocument();
		} else
			return (CandidateDocument) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id,String as) {
		CandidateDocument candidate = findbyIdDocType(id, as);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<CandidateDocument> findAll() {
		List<CandidateDocument> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateDocument").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public CandidateDocument findbyIdDocType(String id,String as) {
		List<CandidateDocument> lstCandidateModels = super.entityManager
				.createQuery("" + "From CandidateDocument where doctype.id=:id and candidate.id =:as").setParameter("id",id).setParameter("as",as).getResultList();
		if (lstCandidateModels.size() == 0) {
			CandidateDocument cds = new CandidateDocument();
			cds.setId("1");
			CandidateModel ss = new CandidateModel();
			ss.setId("1");
			cds.setCandidate(ss);
			return cds;
		} else
			return lstCandidateModels.get(0);
	}
	
	@Transactional
	public Long countCandidateDocType(String id) {
		Long lstCandidateModels =  (Long) super.entityManager
				.createQuery("" + "Select count(*) From CandidateDocument  where doctype.flag=:status and candidate.id=:id").setParameter("id", id).setParameter("status",true).getSingleResult();	
			return lstCandidateModels;
	}
	
}
