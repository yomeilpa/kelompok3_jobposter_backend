package com.Linov.JobPoster.controller;

import java.util.Date;

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

import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.model.JobApplyModel;
import com.Linov.JobPoster.service.JobApplyService;

@RestController
@Controller
@CrossOrigin("*")
public class JobApplyController {
	
	@Autowired
	JobApplyService eds;

	@PostMapping("/jobapply")
	public ResponseEntity<?> insertModel(@RequestBody JobApplyModel education){
		CandidateModel cs = new CandidateModel();
		try {
			
			education.setAppDate(new Date());
			JobApplyModel ss = eds.insertModel(education);
			 cs = ss.getCandidate();
			cs.setPic(null);
			ss.setCandidate(cs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(cs);
	}
	
	@DeleteMapping("/jobapply/{id}")
	public ResponseEntity<?> deleteModel(@PathVariable("id") String id){
		
		try {			
			eds.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/jobapply/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") String id){
		return ResponseEntity.ok(eds.findById(id));
	}
	
	@PutMapping("/jobapply/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody JobApplyModel ed){
		try {
			
			ed.setId(id);
			eds.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(eds);
		
	}
	
}
