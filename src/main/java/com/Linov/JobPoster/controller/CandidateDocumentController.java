package com.Linov.JobPoster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Linov.JobPoster.model.OtherDocumentModel;
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
	
	
	
	@PostMapping("/doc/{id}")
	public ResponseEntity<?> addDocumentCandidate(@PathVariable("id") String id,@RequestPart MultipartFile[] docx,
			@RequestPart String iddoctype){
		try {
			OtherDocumentModel cddoc = new OtherDocumentModel();
			cddoc.setFilename(docx[0].getOriginalFilename());
			cddoc.setDoctype(type.findById(iddoctype));
			cddoc.setType(docx[0].getContentType());
			cddoc.setPic(docx[0].getBytes());
			cddoc.setCandidate(cds.findById(id));
			docs.insertModel(cddoc);
		
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("OK");
	}
}
