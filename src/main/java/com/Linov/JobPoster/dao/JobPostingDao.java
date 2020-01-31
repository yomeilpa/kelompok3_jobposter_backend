package com.Linov.JobPoster.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

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
	public List<JobPostingModel> finByFilter(String provid,String cityId,String title,Double max,Double min) {
		StringBuilder query = new StringBuilder();
		query.append("FROM JobPostingModel jp where 1=1 ");
		if(provid != null) {
			query.append(" and lower(jp.city.province.province) =:f1");
		}
		if(cityId != null) {
			query.append(" and lower(jp.city.city) =:f2");
		}
		if(title != null) {
			query.append(" and lower(jp.title) =:f3");
		}
		if(max != null) {
			query.append(" and jp.salary <=:f4");
		}
		if(min != null) {
			query.append(" and jp.salary >=:f5");
		}
		
		Query exc = super.entityManager.createQuery(query.toString());
		if(provid != null) {
			exc.setParameter("f1", provid);
		}
		if(cityId != null) {
			exc.setParameter("f2", cityId);
		}
		if(title != null) {
			exc.setParameter("f3",title);
		}
		if(max != null) {
			exc.setParameter("f4",max);
		}
		if(min != null) {
			exc.setParameter("f5", min);
		}
		
		List<JobPostingModel> lstCandidateModels = exc.getResultList();
		if (lstCandidateModels.size() == 0) {
			return null;
		} else
			return lstCandidateModels;
	}

}
