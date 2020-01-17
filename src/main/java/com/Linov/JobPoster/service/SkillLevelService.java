package com.Linov.JobPoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.dao.SkillLevelDao;
import com.Linov.JobPoster.model.SkillLevelModel;

@Service
public class SkillLevelService {
	
	@Autowired
	SkillLevelDao acc;
	
	public SkillLevelModel insertModel(SkillLevelModel model) {
		return acc.saveAccount(model);
	}

	public void updateModel(SkillLevelModel model) {
		acc.saveAccount(model);
		;
	}

	public void deleteModel(String id) {
		acc.deleteCandidate(id);
	}
	
	public SkillLevelModel findById(String id) {
		SkillLevelModel model = acc.findbyid(id);
		return model;
	}
	public List<SkillLevelModel> findAll() {
		return acc.findAll();
	}
	
	public void updates(SkillLevelModel skill) {
	acc.update(skill);
	}

}
