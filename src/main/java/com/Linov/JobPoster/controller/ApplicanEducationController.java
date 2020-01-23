package com.Linov.JobPoster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Linov.JobPoster.model.ApplicantEducationModel;
import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.service.AppEducationService;
import com.Linov.JobPoster.service.CandiateService;

@RestController
@Controller
@CrossOrigin("*")
public class ApplicanEducationController {
	
	@Autowired
	AppEducationService cands;
	
	@Autowired
	CandiateService cds;
	
	@PostMapping("/education/candidate/{id}")
	public ResponseEntity<?> insertModel(@RequestBody ApplicantEducationModel education,@PathVariable("id") String id){
		try {
			CandidateModel cs = cds.findById(id);
			education.setCandidate(cs);
			cands.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@PostMapping("/education/candidate")
	public ResponseEntity<?> insertModel(@RequestBody ApplicantEducationModel education){
		try {
			cands.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}	
	
	@DeleteMapping("/education/candidate/{id}")
	public ResponseEntity<?> deleteModel(@PathVariable("id") String id){
		
		try {			
			cands.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/education/candidate/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") String id){
		return ResponseEntity.ok(cands.findById(id));
	}
	
	@GetMapping("/education/candidate/get/{id}")
	public ResponseEntity<?> cand(@PathVariable("id") String id){
		return ResponseEntity.ok(cands.finCd(id));
	}
	
	@PutMapping("/education/candidate/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody ApplicantEducationModel ed){
		try {
			
			ed.setId(id);
			CandidateModel ccds = cands.findById(id).getCandidate();
			ed.setCandidate(ccds);
			cands.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			// TODO: handle exception
		}
		return ResponseEntity.ok(cands);
		
	}
}
