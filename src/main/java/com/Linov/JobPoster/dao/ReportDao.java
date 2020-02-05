package com.Linov.JobPoster.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.DetailReport;
import com.Linov.JobPoster.model.HeaderReport;
import com.Linov.JobPoster.model.JobPostingModel;
import com.Linov.JobPoster.model.JobPostingReport;
import com.Linov.JobPoster.model.ReportPerYear;

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
	
	@SuppressWarnings("unchecked")
	public List<HeaderReport> As(String id){
		List<JobPostingModel> ep = super.entityManager.createQuery("From JobPostingModel where candidate.id =:id").setParameter("id",id).getResultList();
		HeaderReport ss = new HeaderReport();
		ss.setTitle(ep.get(0).getCandidate().getName());
		Long aq = (Long) super.entityManager.createQuery("Select count(*) from JobPostingModel where candidate.id =:id").setParameter("id", ep.get(0).getCandidate().getId()).getSingleResult();
		ss.setTotalPos(aq);
		List<HeaderReport> sd = new ArrayList<HeaderReport>();
		sd.add(ss);
		return sd;
	}
	
	@SuppressWarnings("unchecked")
	public List<DetailReport> gg(){
		List<JobPostingModel> ep = super.entityManager.createQuery("From JobPostingModel").getResultList();
		List<DetailReport> ss = new ArrayList<DetailReport>();
		for(JobPostingModel ks:ep) {
			 DetailReport jj = new DetailReport();
			 jj.setJobTitle(ks.getCandidate().getName());
			 jj.setJobTitle(ks.getTitle());
			 ss.add(jj);
		}
		return ss;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportPerYear> ReportPerYear(){
		List<JobPostingModel> ep = super.entityManager.createQuery("From JobPostingModel").getResultList();
		List<ReportPerYear> ss = new ArrayList<ReportPerYear>();
		for(JobPostingModel ks:ep) {
			ReportPerYear rp = new ReportPerYear();
			rp.setTitle(ks.getTitle());
			rp.setRecruiter(ks.getCandidate().getName());
			rp.setMulai(ks.getStart().toString());
			rp.setBerakhir(ks.getEnd().toString());
			Long acc = (Long) super.entityManager
					.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id and state.state = 'Accepted'").setParameter("id", ks.getId()).getSingleResult();	
			Long rj = (Long) super.entityManager
					.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id and state.state = 'Rejected'").setParameter("id", ks.getId()).getSingleResult();	
			Long total = (Long) super.entityManager
					.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id").setParameter("id", ks.getId()).getSingleResult();	
			
			rp.setAcc(acc);
			rp.setRj(rj);
			rp.setToal(total);
		}
		return ss;
	}
	
}
