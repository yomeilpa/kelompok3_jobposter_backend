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

import com.Linov.JobPoster.model.JobDetailModel;
import com.Linov.JobPoster.model.JobKategoriModel;
import com.Linov.JobPoster.model.JobPosition;
import com.Linov.JobPoster.model.JobPostingModel;
import com.Linov.JobPoster.model.JobRecruitmentModel;
import com.Linov.JobPoster.service.JobDetailService;
import com.Linov.JobPoster.service.JobPositionService;
import com.Linov.JobPoster.service.JobPostingService;
import com.Linov.JobPoster.service.JobRecruitmentService;
import com.Linov.JobPoster.service.JobkategoriService;

@Controller
@RestController
@CrossOrigin("*")
public class JobController {
	
	@Autowired
	private JobkategoriService jobkate;
	
	@Autowired
	private JobPositionService jobpos;
	
	@Autowired
	private JobPostingService jobs;
	
	@Autowired
	private JobDetailService jobdetails;
	
	@Autowired
	private JobRecruitmentService jobrecs;
	
	//Job Kategori Serivce
	@PostMapping("/jobkategori")
	public ResponseEntity<?> insertModel(@RequestBody JobKategoriModel education){
		try {
			jobkate.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@DeleteMapping("/jobkategori/{id}")
	public ResponseEntity<?> deleteModel(@PathVariable("id") String id){
		
		try {			
			jobkate.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/jobkategori/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") String id){
		return ResponseEntity.ok(jobkate.findById(id));
	}
	
	@PutMapping("/jobkategori/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody JobKategoriModel ed){
		try {
			
			ed.setId(id);
			jobkate.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(ed);
		
	}

	//JobPostion Service

	@PostMapping("/jobposition")
	public ResponseEntity<?> insertModel(@RequestBody JobPosition education){
		try {
			jobpos.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@DeleteMapping("/jobposition/{id}")
	public ResponseEntity<?> deleteModels(@PathVariable("id") String id){
		
		try {			
			jobpos.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/jobposition/{id}")
	public ResponseEntity<?> findByids(@PathVariable("id") String id){
		return ResponseEntity.ok(jobpos.findById(id));
	}
	
	@PutMapping("/jobposition/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody JobPosition ed){
		try {
			
			ed.setId(id);
			jobpos.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(ed);
		
	}
	
	//Job Posting Service
	
	@PostMapping("/jobposting")
	public ResponseEntity<?> insertModel(@RequestBody JobPostingModel education){
		try {
			jobs.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@DeleteMapping("/jobposting/{id}")
	public ResponseEntity<?> deleteModelss(@PathVariable("id") String id){
		
		try {			
			jobs.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/jobposting/{id}")
	public ResponseEntity<?> findByidss(@PathVariable("id") String id){
		return ResponseEntity.ok(jobs.findById(id));
	}
	
	@PutMapping("/jobposting/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody JobPostingModel ed){
		try {
			
			ed.setId(id);
			jobs.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(ed);
		
	}
	
	//Job Detail Service
	
	@PostMapping("/jobdetail")
	public ResponseEntity<?> insertModel(@RequestBody JobDetailModel education){
		try {
			jobdetails.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@DeleteMapping("/jobdetail/{id}")
	public ResponseEntity<?> deleteModelsss(@PathVariable("id") String id){
		
		try {			
			jobdetails.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/jobdetail/{id}")
	public ResponseEntity<?> findByidsss(@PathVariable("id") String id){
		return ResponseEntity.ok(jobdetails.findById(id));
	}
	
	@PutMapping("/jobdetail/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody JobDetailModel ed){
		try {
			
			ed.setId(id);
			jobdetails.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(ed);
		
	}
	
	//Job Recruitment Service
	
	@PostMapping("/jobrecruitment")
	public ResponseEntity<?> insertModel(@RequestBody JobRecruitmentModel education){
		try {
			jobrecs.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@DeleteMapping("/jobrecruitment/{id}")
	public ResponseEntity<?> deleteModelssss(@PathVariable("id") String id){
		
		try {			
			jobrecs.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/jobrecruitment/{id}")
	public ResponseEntity<?> findByidssss(@PathVariable("id") String id){
		return ResponseEntity.ok(jobrecs.findById(id));
	}
	
	@PutMapping("/jobrecruitment/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody JobRecruitmentModel ed){
		try {
			
			ed.setId(id);
			jobrecs.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(ed);
		
	}
}
