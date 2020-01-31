package com.Linov.JobPoster.model;

import java.sql.Date;
import java.sql.Time;

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
@Table(name = "list_of_interview")
public class ListofInterviewModel {
	
	public ListofInterviewModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListofInterviewModel(JobApplyModel job, Date date, Time time, InterviewStatusModel status) {
		super();
		this.job = job;
		this.date = date;
		this.time = time;
		this.status = status;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "idjob", nullable = false,unique = true)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private JobApplyModel job;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "time")
	private Time time;
	
	@Column(name = "interview_result",columnDefinition = "TEXT")
	private String resultInt;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "idstatus", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private InterviewStatusModel status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JobApplyModel getJob() {
		return job;
	}

	public void setJob(JobApplyModel job) {
		this.job = job;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public InterviewStatusModel getStatus() {
		return status;
	}

	public void setStatus(InterviewStatusModel status) {
		this.status = status;
	}

	public String getResultInt() {
		return resultInt;
	}

	public void setResultInt(String resultInt) {
		this.resultInt = resultInt;
	}

}
