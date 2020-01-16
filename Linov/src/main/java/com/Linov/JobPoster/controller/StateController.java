package com.Linov.JobPoster.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Linov.JobPoster.model.State_AppliedModel;
import com.Linov.JobPoster.service.StateAplliedService;

@RestController
@Controller
public class StateController {
	
	StateAplliedService eds;

	@PostMapping("/state")
	public ResponseEntity<?> insertModel(@RequestBody State_AppliedModel education){
		try {
			eds.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@DeleteMapping("/state/{id}")
	public ResponseEntity<?> deleteModel(@PathVariable("id") String id){
		
		try {			
			eds.deleteModel(id);;
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/state/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") String id){
		return ResponseEntity.ok(eds.findById(id));
	}
	
	@PutMapping("/state/{id}")
	public ResponseEntity<?> updateModel(@PathVariable("id") String id,@RequestBody State_AppliedModel ed){
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
