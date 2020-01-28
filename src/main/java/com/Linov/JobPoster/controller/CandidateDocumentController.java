package com.Linov.JobPoster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Linov.JobPoster.Validasi.FileValidation;
import com.Linov.JobPoster.model.CandidateDocument;
import com.Linov.JobPoster.service.CandiateService;
import com.Linov.JobPoster.service.DocumentTypeService;
import com.Linov.JobPoster.service.OtherDocumentService;

@RestController
@Controller
@CrossOrigin("*")
public class CandidateDocumentController {
	
	@Autowired
	OtherDocumentService docs;
	
	@Autowired
	CandiateService cds;
	
	
	@Autowired
	DocumentTypeService type;
	
	@Autowired
	FileValidation ses;
	
	
	@PostMapping("/doc/{id}")
	public ResponseEntity<?> addDocumentCandidate(@PathVariable("id") String id,@RequestPart MultipartFile[] docx,
			@RequestPart String iddoctype){
		CandidateDocument cddoc = new CandidateDocument();

		try {
			ses.fileSizeCheck(docx);
			cddoc.setFilename(docx[0].getOriginalFilename());
			cddoc.setDoctype(type.findById(iddoctype));
			cddoc.setType(docx[0].getContentType());
			cddoc.setPic(docx[0].getBytes());
			cddoc.setCandidate(cds.findById(id));
			docs.insertModel(cddoc);
		
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(cddoc);
	}
	
	@PostMapping("add/del")
	public ResponseEntity<?> addAfterDelete(@RequestBody CandidateDocument cds){
		try {
			cds.setId(null);
			docs.insertModel(cds);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(cds);
	}
	
	@GetMapping("/doc/{id}/{iddt}")
	public ResponseEntity<?> getDocTypebyId(@PathVariable("id") String idcd,@PathVariable("iddt") String iddt){
		CandidateDocument cs = docs.findTrue(iddt, idcd);
		cs.setCandidate(null);
		return ResponseEntity.ok(cs);
	}
	
	
	@GetMapping("cd/doc/{id}")
	public ResponseEntity<?> getDocTypebyId(@PathVariable("id") String idcd){
		return ResponseEntity.ok(docs.countCandidateDoc(idcd));
	}
	
	@DeleteMapping("/doc/{id}/{iddt}")
	public ResponseEntity<?> delete(@PathVariable("id") String idcd,@PathVariable("iddt") String iddt){
		try {			
			docs.deleteModel(iddt,idcd);;
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("DELETE GAGAL");
		}
		return ResponseEntity.ok("DELETE SUCCES");	}
}
