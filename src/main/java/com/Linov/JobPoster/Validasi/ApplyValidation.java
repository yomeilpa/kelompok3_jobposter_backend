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
		if(jk.getId() != "x") {
			throw new Exception("You Has Apllied for this Job");
		}
	}
	
	public void cekHired(JobApplyModel sk) throws Exception{
		Long a = jobs.countAcc(sk.getJob().getId());
		Double b = sk.getJob().getSaldo();
		if(a>= b) {
			throw new Exception("Job Quota Is Limited");
		}
		
	}
	public void cekAcc(JobApplyModel job) throws Exception{
		String idCandidate = job.getCandidate().getId();
		JobApplyModel jb = jobs.findAcc(idCandidate);
		if(jb.getId() != "x") {
			throw new Exception("You can't Apply because you has agree to work on "+jb.getJob().getTitle());
		}
	}

}
