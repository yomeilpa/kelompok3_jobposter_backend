package com.Linov.JobPoster.controller;

import java.util.ArrayList;
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

import com.Linov.JobPoster.model.CandidateDocument;
import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.model.DocumentTypeModel;
import com.Linov.JobPoster.service.DocumentTypeService;
import com.Linov.JobPoster.service.OtherDocumentService;

@RestController
@Controller
@CrossOrigin("*")
public class DocumentTypeController {
	
	@Autowired
	DocumentTypeService cands;

	@Autowired
	OtherDocumentService cds;
	
	
	@PostMapping("/doctype")
	public ResponseEntity<?> insertModel(@RequestBody DocumentTypeModel education){
		try {
			cands.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@DeleteMapping("/doctype/{id}")
	public ResponseEntity<?> deleteModel(@PathVariable("id") String id){
		
		try {			
			cands.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/doctype/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") String id){
		return ResponseEntity.ok(cands.findById(id));
	}
	
	@GetMapping("count/doctype")
	public ResponseEntity<?> countDoc(){
		return ResponseEntity.ok(cands.countDoctye());
	}
	
	@GetMapping("/doctype")
	public ResponseEntity<?> findByid(){
		return ResponseEntity.ok(cands.findAll());
	}
	
	@GetMapping("/doctype/true")
	public ResponseEntity<?> findTrue(){
		List<DocumentTypeModel> doc = cands.findTrue();
		return ResponseEntity.ok(doc);
	}
	
	@GetMapping("/doctype/true/{cd}")
	public ResponseEntity<?> findTrue(@PathVariable("cd") String cd){
		List<Object> back = new ArrayList<Object>();
		List<DocumentTypeModel> doc = cands.findTrue();
		for(DocumentTypeModel ls:doc) {
			List<Object> obj = new ArrayList<Object>();
			CandidateDocument c = cds.findTrue(ls.getId(), cd);
			CandidateModel cs = c.getCandidate();
			cs.setPic(null);
			c.setPic(null);
			c.setCandidate(cs);
			obj.add(c);
			obj.add(ls);
			back.add(obj);
		}
		return ResponseEntity.ok(back);
	}
	@PutMapping("/doctype/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody DocumentTypeModel ed){
		try {
			
			ed.setId(id);
			cands.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(ed);
		
	}

}
