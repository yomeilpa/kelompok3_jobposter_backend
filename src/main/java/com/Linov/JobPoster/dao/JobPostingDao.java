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
				.createQuery("" + "From JobPostingModel where active is true").getResultList();
		if (lstCandidateModels.size() == 0) {
			return lstCandidateModels;
		} else
			return lstCandidateModels;
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
