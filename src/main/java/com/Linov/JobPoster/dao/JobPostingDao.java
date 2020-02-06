package com.Linov.JobPoster.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.FilterJobPosting;
import com.Linov.JobPoster.model.JobPostingModel;
import com.Linov.JobPoster.model.ReportPerYear;
import com.Linov.JobPoster.model.ReportbyPoster;

@Repository
@Transactional
public class JobPostingDao  extends CommonDao{
	
	@Transactional
	public JobPostingModel saveAccount(JobPostingModel account) {
		return super.entityManager.merge(account);	
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public JobPostingModel findbyid(String id) {
		List<JobPostingModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPostingModel where id=:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return new JobPostingModel();
		} else
			return (JobPostingModel) lstCandidateModels.get(0);
	}

	@Transactional
	public void deleteCandidate(String id) {
		JobPostingModel candidate = findbyid(id);
		super.entityManager.remove(candidate);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobPostingModel> findAll() {
		List<JobPostingModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPostingModel where active is true").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ReportPerYear> findforReport(String year) {
		List<ReportPerYear> as = new ArrayList<ReportPerYear>();
		List<JobPostingModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPostingModel s where to_char(s.start,'YYYY') =:year or to_char(s.end,'YYYY') =:year").setParameter("year", year).getResultList();
		for(JobPostingModel ss:lstCandidateModels) {
			ReportPerYear se = new ReportPerYear();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy");
			String date = dateFormat.format(ss.getStart());
			
			se.setTitle(ss.getTitle());
			se.setRecruiter(ss.getCandidate().getName());
			se.setMulai(date);
			se.setBerakhir(date);
			Long acc = (Long) super.entityManager
					.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id and state.state = 'Accepted'").setParameter("id", ss.getId()).getSingleResult();	
			Long rj = (Long) super.entityManager
					.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id and state.state = 'Rejected'").setParameter("id", ss.getId()).getSingleResult();	
			Long total = (Long) super.entityManager
					.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id").setParameter("id", ss.getId()).getSingleResult();	
			
			se.setAcc(acc.intValue());
			se.setRj(rj.intValue());
			se.setToal(total.intValue());
			as.add(se);
		}
			
			return as;
	}
	
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ReportbyPoster> findforReportCd(String id) {
		List<ReportbyPoster> as = new ArrayList<ReportbyPoster>();
		List<JobPostingModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPostingModel where candidate.id=:id").setParameter("id",id).getResultList();
		for(JobPostingModel ss:lstCandidateModels) {
			ReportbyPoster se = new ReportbyPoster();
			se.setTitle(ss.getTitle());
			se.setRecruiter(ss.getCandidate().getName());
			se.setMulai(ss.getStart().toString());
			se.setBerakhir(ss.getEnd().toString());
			Long acc = (Long) super.entityManager
					.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id and state.state = 'Accepted'").setParameter("id", ss.getId()).getSingleResult();	
			Long rj = (Long) super.entityManager
					.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id and state.state = 'Rejected'").setParameter("id", ss.getId()).getSingleResult();	
			Long total = (Long) super.entityManager
					.createQuery("" + "Select count(*) From JobApplyModel where job.id=:id").setParameter("id", ss.getId()).getSingleResult();	
			Long sum = (Long) super.entityManager
					.createQuery("" + "Select count(*) From JobPostingModel where candidate.id=:id").setParameter("id", ss.getCandidate().getId()).getSingleResult();	
			se.setRec(ss.getCandidate().getName());
			se.setSum(sum.intValue());
			se.setAcc(acc.intValue());
			se.setRj(rj.intValue());
			se.setToal(total.intValue());
			as.add(se);
		}
			
			return as;
	}
	
	
	
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobPostingModel> findBycandidateid(String id) {
		List<JobPostingModel> lstCandidateModels = super.entityManager
				.createQuery("" + "From JobPostingModel where candidate.id =:id").setParameter("id", id).getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}
	
	public void updateState() {
		super.entityManager.createQuery("update JobPostingModel set active=false where end <= current_date").executeUpdate();
	}
	
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobPostingModel> finByFilter(FilterJobPosting eg) {
		StringBuilder query = new StringBuilder();
		query.append("FROM JobPostingModel jp where 1=1 and active is true");
		if(eg.getProvinsi() != null) {
			query.append(" and jp.city.province.province =:a");
		}
		if(eg.getKota() != null) {
			query.append(" and jp.city.city =:b");
		}
		
		if(eg.getTitle() != null) {
			query.append(" and lower(jp.title) like:f3");
		}
		if(eg.getMaxSalary() != null) {
			query.append(" and jp.salary <=:f4");
		}
		if(eg.getMinSalary() != null) {
			query.append(" and jp.salary >=:f5");
		}
		
		Query exc = super.entityManager.createQuery(query.toString());
		if(eg.getProvinsi() != null) {
			exc.setParameter("a",eg.getProvinsi());
		}
		if(eg.getKota() != null) {	
			exc.setParameter("b",eg.getKota());
		}
		if(eg.getTitle() != null) {
			exc.setParameter("f3","%"+ eg.getTitle().toLowerCase()+"%");
		}
		if(eg.getMaxSalary() != null) {
			exc.setParameter("f4", eg.getMaxSalary());
		}
		if(eg.getMinSalary() != null) {
			exc.setParameter("f5", eg.getMinSalary());
		}
		
		List<JobPostingModel> lstCandidateModels = exc.getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
	}

}
