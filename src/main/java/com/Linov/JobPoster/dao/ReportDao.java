package com.Linov.JobPoster.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.JobPostingModel;
import com.Linov.JobPoster.model.JobPostingReport;

@Repository
@Transactional
public class ReportDao extends CommonDao {

	@SuppressWarnings("unchecked")
	public List<JobPostingReport> oks(){
		List<JobPostingModel> ep = super.entityManager.createQuery("From JobPostingModel").getResultList();
		List<JobPostingReport> ss = new ArrayList<JobPostingReport>();
		for(JobPostingModel ks:ep) {
			 JobPostingReport jj = new JobPostingReport();
			 jj.setJobRec(ks.getCandidate().getName());
			 jj.setJobTitle(ks.getTitle());
			 ss.add(jj);
		}
		return ss;
	}
	
}
