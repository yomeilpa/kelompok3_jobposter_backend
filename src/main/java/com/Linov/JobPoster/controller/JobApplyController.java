package com.Linov.JobPoster.controller;

import java.util.Date;
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

import com.Linov.JobPoster.Validasi.ApplyValidation;
import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.model.JobApplyModel;
import com.Linov.JobPoster.service.JobApplyService;
import com.Linov.JobPoster.service.StateAplliedService;

@RestController
@Controller
@CrossOrigin("*")
public class JobApplyController {
	
	@Autowired
	JobApplyService eds;
	
	@Autowired
	ApplyValidation val;

	@Autowired
	StateAplliedService st;
	
	@PostMapping("/jobapply")
	public ResponseEntity<?> insertModel(@RequestBody JobApplyModel education){
		CandidateModel cs = new CandidateModel();
		try {
			
			education.setState(st.findbyname("Not Reviewed"));
			val.cekDokumen(education.getCandidate().getId());
			val.cekHired(education);
			val.cek(education);
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
	public JobApplyController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("app/{id}/{cd}")
	public ResponseEntity<?>getJobCd1(@PathVariable("id") String id,@PathVariable("cd") String cd){
		return ResponseEntity.ok(eds.findAppCd(id, cd));
	}
	
	@GetMapping("count/app/{id}")
	public ResponseEntity<?>getCountCd(@PathVariable("id") String id){
		return ResponseEntity.ok(eds.countCandidate(id));
	}
	
	@GetMapping("job/app/{id}")
	public ResponseEntity<?>getByJob(@PathVariable("id") String id){
		return ResponseEntity.ok(eds.countCandidate(id));
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
		JobApplyModel js = eds.findById(id);
		CandidateModel cs = js.getCandidate();
		cs.setPic(null);
		js.setCandidate(cs);
		return ResponseEntity.ok(js);
	}
	
	@GetMapping("/jobapply/get/{id}")
	public ResponseEntity<?> findByCandidate(@PathVariable("id") String id){
		List<JobApplyModel> ls = eds.findbyCandidate(id);
		for(JobApplyModel js:ls) {
			CandidateModel cs = js.getCandidate();
			cs.setPic(null);
					js.setCandidate(cs);
		}
		return ResponseEntity.ok(ls);
	}
	
	@GetMapping("/jobapply")
	public ResponseEntity<?> findAll(){
		List<JobApplyModel> ls = eds.findAll();
		for(JobApplyModel js:ls) {
			CandidateModel cs = js.getCandidate();
			cs.setPic(null);
			js.setCandidate(cs);	
		}
		return ResponseEntity.ok(ls);
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
	
	@GetMapping("a/{id}")
	public ResponseEntity<?> countA(@PathVariable("id") String id){
		return ResponseEntity.ok(eds.countAcc(id));
	}
	
}
