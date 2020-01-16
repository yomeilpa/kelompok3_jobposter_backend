package com.Linov.JobPoster.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.SkillLevelModel;

@Transactional
@Repository
public class SkillLevelDao extends CommonDao {

	@Transactional
	public SkillLevelModel saveAccount(SkillLevelModel account) {
		return super.entityManager.merge(account);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public SkillLevelModel findbyid(String id) {
		List<SkillLevelModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From SkillLevelModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new SkillLevelModel();
		} else
			return (SkillLevelModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void update(SkillLevelModel skill) {
		 super.entityManager
				.createQuery("" + "UPDATE SkillLevelModel set code=:code  where id=:id").setParameter("id", skill.getId())
				.setParameter("code", skill.getCode())
				.executeUpdate();
	}

	@Transactional
	public void deleteCandidate(String id) {
		SkillLevelModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<SkillLevelModel> findAll() {
		List<SkillLevelModel> lstCandidateModels = super.entityManager.createQuery("" + "From SkillLevelModel")
				.getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}

}
