package com.Linov.JobPoster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "job_saldo")
public class JobSaldoModel {

	public JobSaldoModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobSaldoModel(JobPostingModel job_posting, Integer saldo) {
		super();
		this.job_posting = job_posting;
		this.saldo = saldo;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idjobposting", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private JobPostingModel job_posting;

	@Column(name = "saldo")
	private Integer saldo;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JobPostingModel getJob_posting() {
		return job_posting;
	}

	public void setJob_posting(JobPostingModel job_posting) {
		this.job_posting = job_posting;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}
	
	
}
