package com.Linov.JobPoster.Validasi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.ApplicantEducationModel;
import com.Linov.JobPoster.service.AppEducationService;

@Service
public class CandidateEducationValidation {
	
	@Autowired
	AppEducationService eps;

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
	public void validaUnq(ApplicantEducationModel ed) throws Exception{
		List<ApplicantEducationModel> eq = eps.findUnq(ed.getCandidate().getId(), ed.getEducation().getId());
		if(eq.size() != 0) {
			throw new Exception("Education for this Level has been registered");
		}
	}
}
