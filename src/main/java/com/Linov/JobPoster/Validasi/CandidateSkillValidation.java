package com.Linov.JobPoster.Validasi;

import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.CandidateSkillModel;

@Service
public class CandidateSkillValidation {
	
	public void validasinonBK(CandidateSkillModel skill) throws Exception{
		if(skill.getCandidate() == null) {
			throw new Exception("Candidate Must Assign");
		}
		if(skill.getSkillname() == null) {
			throw new Exception("Your Skillname must be filled");
		}
		if(skill.getLevel() == null) {
			throw new Exception("Your level must be assign");
		}
	}

}
