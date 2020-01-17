package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.State_AppliedModel;

@Repository
@Transactional
public class StateAppliedDao extends CommonDao {

	@Transactional
	public State_AppliedModel saveAccount(State_AppliedModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public State_AppliedModel findbyid(String id) {
		List<State_AppliedModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From State_AppliedModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new State_AppliedModel();
		} else
			return (State_AppliedModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		State_AppliedModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<State_AppliedModel> findAll() {
		List<State_AppliedModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From State_AppliedModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}


}
