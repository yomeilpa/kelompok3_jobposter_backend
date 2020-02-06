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

import com.Linov.JobPoster.Validasi.CandidateSkillValidation;
import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.model.CandidateSkillModel;
import com.Linov.JobPoster.service.CandiateService;
import com.Linov.JobPoster.service.CandidateSkillService;

@RestController
@Controller
@CrossOrigin("*")
public class CandidateSkillController {
	
	@Autowired
	CandidateSkillService cands;
	
	@Autowired
	CandiateService cds;
	
	@Autowired
	CandidateSkillValidation val;
	
	@PostMapping("/skill/candidate/{id}")
	public ResponseEntity<?> insertModel(@RequestBody CandidateSkillModel education,@PathVariable("id") String id){
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
	
	@DeleteMapping("/skill/candidate/{id}")
	public ResponseEntity<?> deleteModel(@PathVariable("id") String id){
		
		try {			
			cands.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/skill/candidate/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") String id){
		CandidateSkillModel cs = cands.findById(id);
		cs.setCandidate(null);
		return ResponseEntity.ok(cs);
	}
	
	@GetMapping("/skill/candidate/get/{id}")
	public ResponseEntity<?> findByCand(@PathVariable("id") String id){
		List<CandidateSkillModel> ok = cands.findCandidate(id);
		for(CandidateSkillModel sa:ok) {
			sa.setCandidate(null);
		}
		return ResponseEntity.ok(ok);
	}
	
	@PutMapping("/skill/candidate/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody CandidateSkillModel ed){
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
		return ResponseEntity.ok(cands);
		
	}
	
	

}
