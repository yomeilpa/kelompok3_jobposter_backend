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
import com.Linov.JobPoster.model.DocumentTypeModel;
import com.Linov.JobPoster.service.DocumentTypeService;

@RestController
@Controller
public class DocumentTypeController {
	
	@Autowired
	DocumentTypeService cands;
	
	
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
	
	@PutMapping("/doctype/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody DocumentTypeModel ed){
		try {
			
			ed.setId(id);
			cands.updateModel(ed);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update Gagal");
			// TODO: handle exception
		}
		return ResponseEntity.ok(cands);
		
	}

}
