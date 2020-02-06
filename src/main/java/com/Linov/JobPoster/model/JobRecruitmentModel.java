package com.Linov.JobPoster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "job_recruitment",uniqueConstraints = @UniqueConstraint(columnNames = {"idjob","recruitment"}))
public class JobRecruitmentModel {
	
	public JobRecruitmentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobRecruitmentModel(JobPostingModel job, String recruitment) {
		super();
		this.job = job;
		this.recruitment = recruitment;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "idjob", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private JobPostingModel job;
	
	@Column(name = "recruitment")
	private String recruitment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JobPostingModel getJob() {
		return job;
	}

	public void setJob(JobPostingModel job) {
		this.job = job;
	}

	public String getRecruitment() {
		return recruitment;
	}

	public void setRecruitment(String recruitment) {
		this.recruitment = recruitment;
	}





	
}
