package com.Linov.JobPoster.Validasi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.JobApplyModel;
import com.Linov.JobPoster.service.DocumentTypeService;
import com.Linov.JobPoster.service.JobApplyService;
import com.Linov.JobPoster.service.OtherDocumentService;

@Service
public class ApplyValidation {
	
	@Autowired
	OtherDocumentService cand;
	
	@Autowired
	DocumentTypeService docs;
	
	@Autowired 
	JobApplyService jobs;
	
	public void cekDokumen(String id) throws Exception {
		Long cdd = cand.countCandidateDoc(id);
		Long dt = docs.countDoctye();
		if(cdd < dt) {
			throw new Exception("Please Complete Your Document First");
		}
	}
	
	public void cek(JobApplyModel js) throws Exception {
		String idjob = js.getJob().getId();
		String idCandidate = js.getCandidate().getId();
		JobApplyModel jk = jobs.findAppCd(idjob,idCandidate);
		if(jk.getId() == "x") {
			throw new Exception("You Has Apllied for this Job");
		}
	}

}
