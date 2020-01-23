package com.Linov.JobPoster.Validasi;

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
	
	public void idExistrs(CandidateModel candidate) throws Exception {

		if (cands.findById(candidate.getId()) == null) {
			throw new Exception("Candidate Tidak Terdaftar");
		}
	}
	
	public void bkNotchange(CandidateModel candidate,CandidateModel cs) throws Exception {

		if (!candidate.getEmail().equals(cs.getEmail())){
			throw new Exception("Email Not Changed");
		}
		if(!candidate.getPhone().equals(cs.getPhone())) {
			throw new Exception("Phone Not Changed");
		}
	}
	
	

	public void idValidnotNull(CandidateModel candidate) throws Exception {

		if (candidate.getId() == null) {
			throw new Exception("Candidate Not Registered");
		}
	}

	public void validasiBk(CandidateModel candidate) throws Exception {

		if (candidate.getEmail() == null || candidate.getEmail().trim() == "") {
			throw new Exception("Email must be Filled");
		}
		if (candidate.getPhone() == null || candidate.getPhone().trim() == "") {
			throw new Exception("Phone Number must be Filled");
		}
		if (cands.valid(candidate).size() != 0) {
			throw new Exception("User Already Exists");
		}

	}

	public void validasiNonBk(CandidateModel candidate) throws Exception {
		if (candidate.getDob() == null) {
			throw new Exception("Date of Birth Must Be Filled");
		}

		if (candidate.getName() == null) {
			throw new Exception("Name Must Be Filled");
		}

		if (candidate.getGender() == null) {
			throw new Exception("Gender Must Be Filled");
		}
		if (candidate.getAddres() == null) {
			throw new Exception("Addres Must Be Filled");
		}
	}

	public void validasiFK(CandidateModel candidate) throws Exception {

		if (candidate.getCity() == null) {
			throw new Exception("City Must Be Filled");
		}
		if (city.findbyid(candidate.getCity().getId()) == null) {
			throw new Exception("City is Not Registered");
		}
	}

}
