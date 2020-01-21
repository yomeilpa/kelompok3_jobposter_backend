package com.Linov.JobPoster.Validasi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.service.CandiateService;
import com.Linov.JobPoster.service.CityService;

@Service
public class CandidateValidation {

	@Autowired
	CandiateService cands;
	
	@Autowired
	CityService city;

	public void idValid(CandidateModel candidate) throws Exception {

		if (candidate.getId() != null) {
			throw new Exception("ID Must Be Null");
		}
	}

	public void validasiBk(CandidateModel candidate) throws Exception {

		List<CandidateModel> cds = cands.valid(candidate);
		if (candidate.getEmail() == null || candidate.getEmail().trim().equals("")) {
			throw new Exception("Email must be Filled");
		}
		if (candidate.getPhone() == null || candidate.getPhone().trim().equals("")) {
			throw new Exception("Phone Number must be Filled");
		}
		if (cds.size() != 0) {
			throw new Exception("User Already Exists");
		}

	}
	

}
