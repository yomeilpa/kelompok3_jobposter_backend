package com.Linov.JobPoster.controller;

import java.util.ArrayList;
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

import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.model.FilterJobPosting;
import com.Linov.JobPoster.model.JobDetailModel;
import com.Linov.JobPoster.model.JobKategoriModel;
import com.Linov.JobPoster.model.JobPosition;
import com.Linov.JobPoster.model.JobPostingModel;
import com.Linov.JobPoster.model.JobRecruitmentModel;
import com.Linov.JobPoster.service.JobApplyService;
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
	
	@Autowired
	private JobApplyService sk;
	
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
	
	@GetMapping("/jobkategori")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(jobkate.findAll());
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
	
	@GetMapping("/jobposition")
	public ResponseEntity<?> findAllPositon(){
		return ResponseEntity.ok(jobpos.findAll());
	}
	@GetMapping("/jobposition/kate/{id}")
	public ResponseEntity<?> findAllPositonnyKate(@PathVariable("id") String id){
		return ResponseEntity.ok(jobpos.findbyKate(id));
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
		JobPostingModel jb = new JobPostingModel();
		try {
			jb =jobs.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		jb.setCandidate(null);
		return ResponseEntity.ok(jb);
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
		JobPostingModel s = jobs.findById(id);
		CandidateModel k = s.getCandidate();
		k.setPic(null);
		s.setCandidate(k);
		return ResponseEntity.ok(s);
	}
	
	@GetMapping("/jobposting")
	public ResponseEntity<?> findAllJob(){
		//jobs.updateState();
		List<JobPostingModel> ls = jobs.findAll();
		for(JobPostingModel l:ls) {
			CandidateModel s = l.getCandidate();
			s.setPic(null);
			l.setCandidate(s);
		}
		return ResponseEntity.ok(ls);
	}
	
	@PutMapping("/jobposting")
	public ResponseEntity<?> updateall(){
		//jobs.updateState();
		List<JobPostingModel> ls = jobs.findAll();
		for(JobPostingModel l:ls) {
			Date date = new Date();
			if(date.compareTo(l.getEnd()) > 0) {
				l.setActive(false);
				jobs.insertModel(l);
			}
		}
		return ResponseEntity.ok(ls);
	}
	
	@GetMapping("/jobposting/poster/{id}")
	public ResponseEntity<?> findbyPoster(@PathVariable("id") String id){
		//jobs.updateState();
		List<JobPostingModel> ls = jobs.findAllbyPopster(id);
		for(JobPostingModel l:ls) {
			CandidateModel s = l.getCandidate();
			s.setPic(null);
			l.setCandidate(s);
		}
		return ResponseEntity.ok(ls);
	}
	
	
	@GetMapping("/jobposting/poster//quota/{id}")
	public ResponseEntity<?> findbyPosterandQuota(@PathVariable("id") String id){
		List<List<Object>> objL = new ArrayList<List<Object>>();
		//jobs.updateState();
		List<JobPostingModel> ls = jobs.findAllbyPopster(id);
		for(JobPostingModel l:ls) {
			CandidateModel s = l.getCandidate();
			s.setPic(null);
			l.setCandidate(s);
			List<Object> obj = new ArrayList<Object>();
			Long qt = this.sk.countCandidate(l.getId());
			obj.add(qt);
			obj.add(l);
			objL.add(obj);
		}
		return ResponseEntity.ok(objL);
	}
	
	@PostMapping("/jobposting/filter")
	public ResponseEntity<?> finJobbyfilter(@RequestBody FilterJobPosting eg){
		List<JobPostingModel> ls = jobs.findbyFiletr(eg);
		for(JobPostingModel l:ls) {
		
			CandidateModel s = l.getCandidate();
			s.setPic(null);
			l.setCandidate(s);
		}
		return ResponseEntity.ok(ls);
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
		CandidateModel cs = ed.getCandidate();
		cs.setPic(null);
		ed.setCandidate(cs);
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
	
	@PostMapping("/jobdetail/{id}")
	public ResponseEntity<?> insertModel(@RequestBody List<JobDetailModel> education,@PathVariable("id") String idJb){
		try {
			JobPostingModel jb = jobs.findById(idJb);
			for(JobDetailModel as:education) {
				as.setJob(jb);
				jobdetails.insertModel(as);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		for(JobDetailModel sk:education) {
			JobPostingModel s = sk.getJob();
			s.setCandidate(null);
			sk.setJob(s);
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
		JobDetailModel s = jobdetails.findById(id);
		JobPostingModel js = s.getJob();
		js.setCandidate(null);
		s.setJob(js);
		
		return ResponseEntity.ok(s);
	}
	
	@GetMapping("/jobdetail")
	public ResponseEntity<?> findBAllDetail(){
		List<JobDetailModel> js = jobdetails.findAll();
		for(JobDetailModel s:js) {
			JobPostingModel jb = s.getJob();
			jb.setCandidate(null);
			s.setJob(jb);
		}
		return ResponseEntity.ok(js);
	}
	
	@GetMapping("/jobdetail/get/{id}")
	public ResponseEntity<?> findByJob(@PathVariable("id") String id){
		List<JobDetailModel> js = jobdetails.findbyJobP(id);
		for(JobDetailModel s:js) {
			JobPostingModel jb = s.getJob();
			jb.setCandidate(null);
			s.setJob(jb);
		}
		return ResponseEntity.ok(js);
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
		JobPostingModel jb = ed.getJob();
		jb.setCandidate(null);
		ed.setJob(jb);
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
	
	@PostMapping("/jobrecruitment/{id}")
	public ResponseEntity<?> insertModelNe(@RequestBody List<JobRecruitmentModel> education,@PathVariable("id") String idJb){
		try {
			JobPostingModel jb = jobs.findById(idJb);
			for(JobRecruitmentModel as:education) {
				as.setJob(jb);
				jobrecs.insertModel(as);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		for(JobRecruitmentModel s:education) {
			JobPostingModel jb = s.getJob();
			jb.setCandidate(null);
			s.setJob(jb);
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
		JobRecruitmentModel s = jobrecs.findById(id);
		JobPostingModel jb = s.getJob();
		jb.setCandidate(null);
		s.setJob(jb);
		return ResponseEntity.ok(s);
	}
	
	@GetMapping("/jobrecruitment")
	public ResponseEntity<?> findByAll(){
		List<JobRecruitmentModel> js = jobrecs.findAll();
		for(JobRecruitmentModel s:js) {
			JobPostingModel jb = s.getJob();
			jb.setCandidate(null);
			s.setJob(jb);
		}
		return ResponseEntity.ok(js);
	}
	
	@GetMapping("/jobrecruitment/get/{id}")
	public ResponseEntity<?> findByJobs(@PathVariable("id") String id){
		List<JobRecruitmentModel> js = jobrecs.findbyJobPos(id);
		for(JobRecruitmentModel s:js) {
			JobPostingModel jb = s.getJob();
			jb.setCandidate(null);
			s.setJob(jb);
		}
		return ResponseEntity.ok(js);
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
		JobPostingModel jb = ed.getJob();
		jb.setCandidate(null);
		ed.setJob(jb);
		return ResponseEntity.ok(ed);
		
	}
}
