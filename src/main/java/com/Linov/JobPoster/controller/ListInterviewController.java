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

import com.Linov.JobPoster.Validasi.InterviewValidation;
import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.model.JobApplyModel;
import com.Linov.JobPoster.model.ListofInterviewModel;
import com.Linov.JobPoster.service.EmailService;
import com.Linov.JobPoster.service.InterviewStatusService;
import com.Linov.JobPoster.service.ListInterviewService;

@RestController
@Controller
@CrossOrigin("*")
public class ListInterviewController {
	
	@Autowired
	ListInterviewService eds;
	
	@Autowired
	EmailService ems;
	
	@Autowired
	InterviewStatusService ints;
	
	@Autowired
	InterviewValidation val;

	@PostMapping("interview")
	public ResponseEntity<?> insertModel(@RequestBody ListofInterviewModel education){
		try {
			val.validasiNotFk(education);
			eds.insertModel(education);
			ems.sendInvitation(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@PostMapping("interview/reinvite")
	public ResponseEntity<?> reinvite(@RequestBody ListofInterviewModel education){
		try {
			val.validasiNotFk(education);
			eds.insertModel(education);
			ems.sendInvitation(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	@PostMapping("interview/result")
	public ResponseEntity<?> insertModelUpdate(@RequestBody ListofInterviewModel education){
		try {
			val.validasiNotFk(education);
			education.setStatus(ints.findByName("Attend"));
			eds.insertModel(education);
			ems.sendResult(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@DeleteMapping("interview/{id}")
	public ResponseEntity<?> deleteModel(@PathVariable("id") String id){
		
		try {			
			eds.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("interview/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") String id){
		return ResponseEntity.ok(eds.findById(id));
	}
	
	@PutMapping("interview/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody ListofInterviewModel ed){
		try {
			
			ed.setId(id);
			val.validasiNotFk(ed);
			eds.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(eds);
		
	}
	
	@GetMapping("/interview/accepted/{id}")
	public ResponseEntity<?> updateAcc(@PathVariable("id") String id){
		try {
			
			ListofInterviewModel ed = eds.findById(id);
			val.validasiNotFk(ed);
			ed.setStatus(ints.findByName("WILL ATTEND"));
			eds.updateModel(ed);
			ems.sendAcc(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok("Update Succes");
	}
	
	@GetMapping("/interview/rejected/{id}")
	public ResponseEntity<?> updateReject(@PathVariable("id") String id){
		try {
			
			ListofInterviewModel ed = eds.findById(id);
			ed.setStatus(ints.findByName("Rejected"));
			val.validasiNotFk(ed);
			eds.updateModel(ed);
			ems.sendInvReject(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok("Update Succes");
	}
	
	@GetMapping("/hr/interview/rejected/{id}")
	public ResponseEntity<?> updateRejectedbyHr(@PathVariable("id") String id){
		try {
			
			ListofInterviewModel ed = eds.findById(id);
			ed.setStatus(ints.findByName("Rejected"));
			val.validasiNotFk(ed);
			eds.updateModel(ed);
			ems.sendInvReject(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok("Update Succes");
	}
	
	@GetMapping("/interview/request/{id}")
	public ResponseEntity<?> updateRequest(@PathVariable("id") String id){
		try {
			
			ListofInterviewModel ed = eds.findById(id);
			ed.setStatus(ints.findByName("Request New Schedule"));
			val.validasiNotFk(ed);
			eds.updateModel(ed);
			ems.sendReschedule(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok("Update Succes");
	}
	
	@GetMapping("interview/get/{id}")
	public ResponseEntity<?> getIntCd(@PathVariable("id") String id){
		List<ListofInterviewModel> js = eds.findIntCd(id);
		for(ListofInterviewModel as:js) {
			CandidateModel ss = as.getJob().getCandidate();
			ss.setPic(null);
			JobApplyModel ls = as.getJob();
			ls.setCandidate(ss);
			as.setJob(ls);
		}
		return ResponseEntity.ok(js);
	}
	@GetMapping("/interview")
	public ResponseEntity<?> getInt(){
		List<ListofInterviewModel> js = eds.findAll();
		for(ListofInterviewModel as:js) {
			CandidateModel ss = as.getJob().getCandidate();
			ss.setPic(null);
			JobApplyModel ls = as.getJob();
			ls.setCandidate(ss);
			as.setJob(ls);
		}
		return ResponseEntity.ok(js);
	}
	
	@GetMapping("/interview/attd/{id}")
	public ResponseEntity<?> getIntAttend(@PathVariable("id") String id){
		List<ListofInterviewModel> js = eds.findAllAttend(id);
		for(ListofInterviewModel as:js) {
			CandidateModel ss = as.getJob().getCandidate();
			ss.setPic(null);
			JobApplyModel ls = as.getJob();
			ls.setCandidate(ss);
			as.setJob(ls);
		}
		return ResponseEntity.ok(js);
	}
	
	@GetMapping("/interview/posters/{id}")
	public ResponseEntity<?> getIntbyPoster(@PathVariable("id") String id){
		List<ListofInterviewModel> js = eds.findAllbyPoster(id);
		for(ListofInterviewModel as:js) {
			CandidateModel ss = as.getJob().getCandidate();
			ss.setPic(null);
			JobApplyModel ls = as.getJob();
			ls.setCandidate(ss);
			as.setJob(ls);
		}
		return ResponseEntity.ok(js);
	}

}
