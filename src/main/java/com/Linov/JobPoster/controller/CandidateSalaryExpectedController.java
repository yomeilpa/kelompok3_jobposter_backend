package com.Linov.JobPoster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.model.CandidateSalaryExpectedModel;
import com.Linov.JobPoster.service.CandiateService;
import com.Linov.JobPoster.service.CandidateSalaryExpectedService;

@RestController
@Controller
public class CandidateSalaryExpectedController {
	
	@Autowired
	CandidateSalaryExpectedService cands;
	
	@Autowired
	CandiateService cds;
	
	@PostMapping("/salary/candidate/{id}")
	public ResponseEntity<?> insertModel(@RequestBody CandidateSalaryExpectedModel education,@PathVariable("id") String id){
		try {
			CandidateModel cs = cds.findById(id);
			education.setCandidate(cs);
			cands.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@DeleteMapping("/salary/candidate/{id}")
	public ResponseEntity<?> deleteModel(@PathVariable("id") String id){
		
		try {			
			cands.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/salary/candidate/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") String id){
		return ResponseEntity.ok(cands.findById(id));
	}
	
	@PutMapping("/salary/candidate/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody CandidateSalaryExpectedModel ed){
		try {
			
			ed.setId(id);
			ed.setCandidate(cands.findById(id).getCandidate());
			cands.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(cands);
		
	}
	


}
