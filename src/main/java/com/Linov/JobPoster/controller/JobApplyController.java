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
import com.Linov.JobPoster.model.State_AppliedModel;
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
		return ResponseEntity.ok(education);
	}
	public JobApplyController() {
		// TODO Auto-generated constructor stub
	}
	@GetMapping("/jobapply/review/{id}")
	public ResponseEntity<?> stateReview(@PathVariable("id") String id){
		CandidateModel cs = new CandidateModel();
		try {
			
			JobApplyModel education = eds.findById(id);
			State_AppliedModel s = education.getState();
			if(s == st.findbyname("Rejected")){
				return ResponseEntity.ok(education);
			}
			if(education.getState().equals(st.findbyname("Accepted"))){
				return ResponseEntity.ok(education);

			}
			if(education.getState().equals(st.findbyname("ON INVITATION"))){
				return ResponseEntity.ok(education);

			}
			else {
				education.setState(st.findbyname("Reviewed"));

			}
			JobApplyModel ss = eds.insertModel(education);
			 cs = ss.getCandidate();
			ss.setCandidate(cs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("Update Succes");
	}
	
	@GetMapping("/jobapply/rejected/{id}")
	public ResponseEntity<?> stateRejected(@PathVariable("id") String id){
		CandidateModel cs = new CandidateModel();
		try {
		
			JobApplyModel education = eds.findById(id);
			education.setState(st.findbyname("Rejected"));
			JobApplyModel ss = eds.insertModel(education);
			 cs = ss.getCandidate();
			ss.setCandidate(cs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("Update Rejected");
	}
	
	@GetMapping("/jobapply/accepted/{id}")
	public ResponseEntity<?> stateAcceptes(@PathVariable("id") String id){
		CandidateModel cs = new CandidateModel();
		try {
		
			JobApplyModel education = eds.findById(id);
			education.setState(st.findbyname("Accepted"));
			JobApplyModel ss = eds.insertModel(education);
			 cs = ss.getCandidate();
			ss.setCandidate(cs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("Update Rejected");
	}
	
	@GetMapping("/jobapply/invitation/{id}")
	public ResponseEntity<?> oninvt(@PathVariable("id") String id){
		CandidateModel cs = new CandidateModel();
		try {
			
			JobApplyModel education = eds.findById(id); 
			education.setState(st.findbyname("ON INVITATION"));
			JobApplyModel ss = eds.insertModel(education);
			 cs = ss.getCandidate();
			ss.setCandidate(cs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("Update Rejected");
	}
	@GetMapping("app/{id}/{cd}")
	public ResponseEntity<?>getJobCd1(@PathVariable("id") String id,@PathVariable("cd") String cd){
		JobApplyModel jos = eds.findAppCd(id, cd);
		CandidateModel cs = jos.getCandidate();
		cs.setPic(null);
		jos.setCandidate(cs);
		return ResponseEntity.ok(jos);
	}
	
	@GetMapping("count/app/{id}")
	public ResponseEntity<?>getCountCd(@PathVariable("id") String id){
		return ResponseEntity.ok(eds.countCandidate(id));
	}
	
	@GetMapping("job/app/{id}")
	public ResponseEntity<?>getByJob(@PathVariable("id") String id){
		JobApplyModel ss = eds.findById(id);
		CandidateModel sa = ss.getCandidate();
		sa.setPic(null);
		ss.setCandidate(sa);
		return ResponseEntity.ok(ss);
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
	
	@GetMapping("/jobapply/get/acc")
	public ResponseEntity<?> findByAcc(){
		List<JobApplyModel> ls = eds.findBAcc();
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
