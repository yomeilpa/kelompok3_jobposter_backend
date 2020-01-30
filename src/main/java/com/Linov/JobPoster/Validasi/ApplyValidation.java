package com.Linov.JobPoster.Validasi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.service.DocumentTypeService;
import com.Linov.JobPoster.service.OtherDocumentService;

@Service
public class ApplyValidation {
	
	@Autowired
	OtherDocumentService cand;
	
	@Autowired
	DocumentTypeService docs;
	
	public void cekDokumen(CandidateModel cs) throws Exception {
		String id = cs.getId();
		Long cdd = cand.countCandidateDoc(id);
		Long dt = docs.countDoctye();
		if(cdd < dt) {
			throw new Exception("Please Complete Your Document First");
		}
	}

}
