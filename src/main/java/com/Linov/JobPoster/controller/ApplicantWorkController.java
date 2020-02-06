package com.Linov.JobPoster.controller;

import java.util.List;

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

import com.Linov.JobPoster.Validasi.CandidateWorkValidation;
import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.model.WorkExperienceHeader;
import com.Linov.JobPoster.service.CandiateService;
import com.Linov.JobPoster.service.WorkExperienceService;

@RestController
@Controller
@CrossOrigin("*")
public class ApplicantWorkController {
	

	@Autowired
	WorkExperienceService cands;
	
	@Autowired
	CandiateService cds;
	
	@Autowired
	CandidateWorkValidation val;
	
	@PostMapping("/experience/candidate/{id}")
	public ResponseEntity<?> insertModel(@RequestBody WorkExperienceHeader education,@PathVariable("id") String id){
		try {
			CandidateModel cs = cds.findById(id);
			education.setCandidate(cs);
			val.validasinonBK(education);
			cands.insertModel(education);
			education.setCandidate(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@DeleteMapping("/experience/candidate/{id}")
	public ResponseEntity<?> deleteModel(@PathVariable("id") String id){
		
		try {			
			cands.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/experience/candidate/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") String id){
		WorkExperienceHeader cs = cands.findById(id);
		cs.setCandidate(null);
		return ResponseEntity.ok(cs);
	}
	
	@GetMapping("/experience/candidate/get/{id}")
	public ResponseEntity<?> findBCandidate(@PathVariable("id") String id){
		List<WorkExperienceHeader> cs = cands.findCandidate(id);
		for(WorkExperienceHeader ds:cs) {
			ds.setCandidate(null);
		}
		return ResponseEntity.ok(cs);
	}
	
	@PutMapping("/experience/candidate/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody WorkExperienceHeader ed){
		try {
			
			ed.setId(id);
			ed.setCandidate(cands.findById(id).getCandidate());
			val.validasinonBK(ed);
			cands.updateModel(ed);
			ed.setCandidate(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(ed);
		
	}
}
