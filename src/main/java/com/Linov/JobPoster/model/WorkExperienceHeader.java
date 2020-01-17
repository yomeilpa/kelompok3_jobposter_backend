package com.Linov.JobPoster.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "applicant_work_experience")
public class WorkExperienceHeader {
	
	public WorkExperienceHeader(CandidateModel candidate, String name, Double salary, Date mulai, Date berakhir) {
		super();
		this.candidate = candidate;
		this.name = name;
		this.salary = salary;
		this.mulai = mulai;
		this.berakhir = berakhir;
	}

	public WorkExperienceHeader() {
		super();
		// TODO Auto-generated constructor stub
	}


	@OneToOne(optional = false)
	@JoinColumn(name = "idcandidate", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CandidateModel candidate;
	
	@Column(name ="namecompany")
	 private String name;
	 
	 @Column(name ="salary")
	 private Double salary;
	 
	 @Column(name = "mulai")
	 private Date mulai;
	 
	 @Column(name = "berakhir")
	 private Date berakhir;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	public CandidateModel getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateModel candidate) {
		this.candidate = candidate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getMulai() {
		return mulai;
	}

	public void setMulai(Date mulai) {
		this.mulai = mulai;
	}

	public Date getBerakhir() {
		return berakhir;
	}

	public void setBerakhir(Date berakhir) {
		this.berakhir = berakhir;
	}



	
}
