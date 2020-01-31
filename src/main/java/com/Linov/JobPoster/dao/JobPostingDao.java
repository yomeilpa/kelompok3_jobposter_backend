package com.Linov.JobPoster.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Linov.JobPoster.model.FilterJobPosting;
import com.Linov.JobPoster.model.JobPostingModel;

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
				.createQuery("" + "From JobPostingModel").getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}
	
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<JobPostingModel> finByFilter(FilterJobPosting eg) {
		StringBuilder query = new StringBuilder();
		query.append("FROM JobPostingModel where 1=1");
		if(eg.getProvince()!= null) {
			query.append(" and city.province =:f1");
		}
		if(eg.getCity() != null) {
			query.append(" and city =:f2");
		}
		if(eg.getTitle() != null) {
			query.append(" and lower(title) like:f3");
		}
		if(eg.getMaxSalary() != null) {
			query.append(" and salary <=:f4");
		}
		if(eg.getMinSalary() != null) {
			query.append(" and salary >=:f5");
		}
		
		Query exc = super.entityManager.createQuery(query.toString());
		if(eg.getProvince() != null) {
			String prov = eg.getProvince().getProvince();
			exc.setParameter("f1",eg.getProvince());
		}
		if(eg.getCity() != null) {
			String city = eg.getCity().getCity();
			exc.setParameter("f2",eg.getCity());
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
