package com.Linov.JobPoster.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Linov.JobPoster.Validasi.CandidateValidation;
import com.Linov.JobPoster.Validasi.FileValidation;
import com.Linov.JobPoster.config.JwtTokenUtil;
import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.model.UserModel;
import com.Linov.JobPoster.service.AccountService;
import com.Linov.JobPoster.service.CandiateService;
import com.Linov.JobPoster.service.EmailService;

@RestController
@CrossOrigin("*")
public class RegistrationController {
	
	private final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AccountService userService;
	

	
	@Autowired
	CandidateValidation cd;

	@Autowired
	AccountService account;
	
	@Autowired
	EmailService email;
	
	@Autowired
	CandiateService candidate;
	
	@Autowired
	FileValidation ses;
	
	
	@PostMapping("/register/admin")
	public ResponseEntity<?> registerUserAdmin(@RequestBody CandidateModel cand){
		String password = randomAlphaNumeric(10);
		
		try {
			cd.idValid(cand);
			cd.validasiNonBkAdmin(cand);
			cd.validasiBk(cand);
			cd.validasiFK(cand);
			CandidateModel cant = candidate.insertModel(cand);
			UserModel users = new UserModel(password, cant,"HR");
			account.insertModel(users);
			email.sendNotid(cand.getEmail(), password, cand.getEmail());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
		return ResponseEntity.ok(cand);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody CandidateModel cand) throws Exception{
		String password = randomAlphaNumeric(10);
		
		try {
			cd.idValid(cand);
			cd.validasiNonBk(cand);
			cd.validasiBk(cand);
			cd.validasiFK(cand);
			CandidateModel cant = candidate.insertModel(cand);
			UserModel users = new UserModel(password, cant,"applicant");
			account.insertModel(users);
			email.sendNotid(cand.getEmail(), password, cand.getEmail());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(cand);
	}
		
	
	@PostMapping("/uploadphoto/{id}")
	public ResponseEntity<?> uploadiProfile(@RequestPart("upload") MultipartFile[] upload,CandidateModel candidate,@PathVariable("id") String id){
		candidate = this.candidate.findById(id);
		try {
			ses.fileSizeCheck(upload);;
			cd.idValidnotNull(candidate);
			cd.validasiNonBk(candidate);
			cd.validasiFK(candidate);
			ses.fileSizeCheck(upload);
			candidate.setFilename(upload[0].getOriginalFilename());
			candidate.setType(upload[0].getContentType());
			byte[] byteArr = upload[0].getBytes();
			candidate.setPic(byteArr);
			this.candidate.updateModel(candidate);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(candidate);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> uploadiProfile(@RequestBody CandidateModel candidate,@PathVariable("id") String id){
		CandidateModel set = this.candidate.findById(id);
		
		candidate.setId(id);
		candidate.setFilename(set.getFilename());
		candidate.setPic(set.getPic());
		candidate.setType(set.getType());
		try {
			cd.idValidnotNull(candidate);	
			cd.validasiNonBk(candidate);
			cd.bkNotchange(candidate, set);
			cd.validasiFK(candidate);
			this.candidate.updateModel(candidate);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(candidate);
	}
	
	@PutMapping("/update/password/{id}")
	public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> data,@PathVariable("id") String id){
		UserModel us = account.findById(id);
		String oldPassword = us.getPassword();
		
		try {
			if(us.getCandidate() == null) {
				return ResponseEntity.badRequest().body("Not Find");
			}
			else {
			if(oldPassword.equals(data.get("old")))
			{
				String np1 = data.get("newPas1");
				String np2 = data.get("newPas2");
				if(np1.equals(np2)) {
					us.setPassword(np1);
				}
				else {
					return ResponseEntity.badRequest().body("Password did not match");
				}
			}
			else {
				return ResponseEntity.badRequest().body("Old Password incorrect");
			}
			}
			
			account.insertModel(us);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(us);
	}
	
	@GetMapping("count")
	public ResponseEntity<?> count(){
		return ResponseEntity.ok(account.countCand());
	}
	
	@DeleteMapping("/candidate/{id}")
	public ResponseEntity<?> deleteCandidate(@PathVariable("id") String id){
		try {
			CandidateModel cda = this.candidate.findById(id);
			cd.idValidnotNull(cda);
			candidate.deleteModel(id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("DELETE SUCCES");
	}
	
	@GetMapping("/account")
	public ResponseEntity<?> findallUsername(){
		return ResponseEntity.ok(account.findAll());
	}
	
	@GetMapping("/candidate")
	public ResponseEntity<?> getCand(){
		return ResponseEntity.ok(candidate.findAll());
	}
	
	@GetMapping("/candidate/{id}")
	public ResponseEntity<?> getCand(@PathVariable("id") String id){
		return ResponseEntity.ok(candidate.findById(id));
	}
	
	@GetMapping("/candidate/name/{id}")
	public ResponseEntity<?> getCandbyName(@PathVariable("id") String name){
		return ResponseEntity.ok(candidate.findByname(name));
	}
	
	public String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	@PostMapping("/daftar")
	public ResponseEntity<?> createProfile(@RequestPart MultipartFile[] upload,@RequestPart CandidateModel candidate){
		try {
			candidate.setFilename(upload[0].getOriginalFilename());
			candidate.setType(upload[0].getContentType());
			byte[] byteArr = upload[0].getBytes();
			candidate.setPic(byteArr);
			CandidateModel us = this.candidate.insertModel(candidate);
			UserModel users = new UserModel("aaaa", us,"applicant");
			account.insertModel(users);
			email.sendNotid(us.getEmail(), "aaaa", us.getEmail());
		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(candidate);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> sign){
		UserModel user;
		try {
			 user = account.findbyEmail(sign.get("username"));
			if(user != null) {
				String password = user.getPassword();
				if(password.equals(sign.get("password"))) {
					return ResponseEntity.ok(user);
				}
				else {
					return ResponseEntity.badRequest().body("Password Salah");
				}			
			}	
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Username Tidak Terdaftar");
			// TODO: handle exception
		}
		return ResponseEntity.ok(user);
	}
	@PostMapping("jwt/login")
	public ResponseEntity<?> insertUser(@RequestBody Map<String, String> account) throws Exception {
//		UserList user;
//		try {
//			user = userService.login(account.get("username"), account.get("password"));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body(user);
		UserModel users;
		try {
			 users = this.account.findbyEmail(account.get("username"));
				if(users != null) {
					String password = users.getPassword();
					if(password.equals(account.get("password"))) {
					}
					else {
						return ResponseEntity.badRequest().body("Password Salah");
					}			
				}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Username Tidak Terdaftar");
			// TODO: handle exception
		}
		List<Object> session = new ArrayList<Object>();
		
		UserModel user = userService.findbyEmail(account.get("username"));
		
		authenticate(account.get("username"), account.get("password"));
		
		final UserDetails userDetails = userService
				.loadUserByUsername(account.get("username"));
		final String token = jwtTokenUtil.generateToken(userDetails);
		user.setPassword(null);
		session.add(user);
		session.add(token);

	
		return ResponseEntity.ok(session);
		
	}
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
