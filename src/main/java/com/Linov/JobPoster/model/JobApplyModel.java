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
@Table(name = "apply_job",uniqueConstraints = {@UniqueConstraint(columnNames = {"idcandidate","idjob"})})
public class JobApplyModel {
	
	public JobApplyModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobApplyModel(CandidateModel candidate, JobPostingModel job) {
		super();
		this.candidate = candidate;
		this.job = job;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "idcandidate", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CandidateModel candidate;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "idjob", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private JobPostingModel job;

	
	@ManyToOne(optional = true)
	@JoinColumn(name = "idstate", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private State_AppliedModel state;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CandidateModel getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateModel candidate) {
		this.candidate = candidate;
	}

	public JobPostingModel getJob() {
		return job;
	}

	public void setJob(JobPostingModel job) {
		this.job = job;
	}

	public State_AppliedModel getState() {
		return state;
	}

	public void setState(State_AppliedModel state) {
		this.state = state;
	}
	
	

}
