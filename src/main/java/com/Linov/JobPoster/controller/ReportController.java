package com.Linov.JobPoster.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Linov.JobPoster.service.JobPostingReportService;

@Controller
@RestController
@CrossOrigin("*")
public class ReportController {
	
	@Autowired
	private JobPostingReportService js;
	
	@GetMapping("/report1/{format}")
	public ResponseEntity<?> correctPerPackage(@PathVariable String format, HttpServletRequest request) 
			throws Exception {
		
		String fileName = js.correctPerPackage(format);
		
		// Load file as Resource
        Resource resource = js.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    	
	}
	
	@GetMapping("/reportyear/{format}")
	public ResponseEntity<?> reportPerYear(@PathVariable String format, HttpServletRequest request) 
			throws Exception {
		
		String fileName = js.ReportPerYears(format);
		
		// Load file as Resource
        Resource resource = js.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);	
	}
	
	
	
	
	
	@GetMapping("/report1/{id}/{format}")
	public ResponseEntity<?> sub(@PathVariable("id") String id, @PathVariable String format, HttpServletRequest request) 
			throws Exception {
		
		String fileName = js.header(format, id);
		
		// Load file as Resource
        Resource resource = js.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    	
	}

}
