package com.Linov.JobPoster.Validasi;

import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.ApplicantEducationModel;

@Service
public class CandidateEducationValidation {

	public void validasinonBk(ApplicantEducationModel ed) throws Exception{
		if(ed.getName() == null  || ed.getName().trim().equals("")) {
			throw new Exception("Name of School or University must be filled");
		}
		if(ed.getEducation()== null) {
			throw new Exception("Education Level Must Be Choosed");
		}
		if(ed.getGpa() == null) {
			throw new Exception("Gpa Must Be Filled");
		}
		if(ed.getMulai() == null) {
			throw new Exception("Your Start Education Must Be Filled");
		}
		if(ed.getBerakhir() == null) {
			throw new Exception("Your End Date Education Must Be Filled");
		}
		if(ed.getCandidate() == null) {
			throw new Exception("Candidate Must Assign");
		}
	}
}
