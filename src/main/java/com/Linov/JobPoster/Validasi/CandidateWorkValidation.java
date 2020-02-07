package com.Linov.JobPoster.Validasi;

import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.WorkExperienceHeader;

@Service
public class CandidateWorkValidation {
	
	public void validasinonBK(WorkExperienceHeader work) throws Exception{
		if(work.getName() == null || work.getName().trim().equals("")) {
			throw new Exception("Name of your Company must be filled");
		}
		if(work.getPosition() == null || work.getPosition().trim().equals("")) {
			throw new Exception("Position must be choosed");
		}
		
		if(work.getMulai() == null) {
			throw new Exception("Your Start work date Must Be Filled");
		}
		if(work.getBerakhir() == null) {
			throw new Exception("Your End Date work Must Be Filled");
		}
		
		if(work.getSalary() == null) {
			throw new Exception("Your Salary Must Be Filled");
		}
		if(work.getCandidate() == null) {
			throw new Exception("Candidate must be assign");
		}
		
		

	}
}
