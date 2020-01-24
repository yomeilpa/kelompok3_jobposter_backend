package com.Linov.JobPoster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Linov.JobPoster.model.SkillLevelModel;
import com.Linov.JobPoster.service.SkillLevelService;

@RestController
@Controller
@CrossOrigin("*")
public class SkillLevelController {

	@Autowired
	SkillLevelService cands;
	
	
	@PostMapping("/skill")
	public ResponseEntity<?> insertModel(@RequestBody SkillLevelModel education){
		try {
			cands.insertModel(education);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(education);
	}
	
	@PutMapping("updates")
	public ResponseEntity<?> update(@RequestBody SkillLevelModel skill){
		try {
			cands.updates(skill);
			return ResponseEntity.ok("ok");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping("/skill")
	public ResponseEntity<?> findByid(){
		return ResponseEntity.ok(cands.findAll());
	}
}
