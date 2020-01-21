package com.Linov.JobPoster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "applied_status")
public class JobAppliedStatus {

	public JobAppliedStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobAppliedStatus(JobApplyModel jobapply, State_AppliedModel state) {
		super();
		this.jobapply = jobapply;
		this.state = state;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "idjobapply", nullable = false,unique = true)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private JobApplyModel jobapply;
	
	@ManyToOne	(optional = false)
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

	public JobApplyModel getJobapply() {
		return jobapply;
	}

	public void setJobapply(JobApplyModel jobapply) {
		this.jobapply = jobapply;
	}

	public State_AppliedModel getState() {
		return state;
	}

	public void setState(State_AppliedModel state) {
		this.state = state;
	}


}
