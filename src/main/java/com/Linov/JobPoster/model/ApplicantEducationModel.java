package com.Linov.JobPoster.model;

import java.sql.Date;

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
@Table(name = "applicant_education",uniqueConstraints = {@UniqueConstraint(columnNames = {"idcandidate","ideducation"})})
public class ApplicantEducationModel {
	
	public ApplicantEducationModel(CandidateModel candidate, EducationModel education, String name, String gpa,
			Date mulai, Date berakhir) {
		super();
		this.candidate = candidate;
		this.education = education;
		this.name = name;
		this.gpa = gpa;
		this.mulai = mulai;
		this.berakhir = berakhir;
	}

	public ApplicantEducationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@ManyToOne(optional = false)
	@JoinColumn(name = "idcandidate", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CandidateModel candidate;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	 	@ManyToOne(optional = false)
		@JoinColumn(name = "ideducation", nullable = false)
		@OnDelete(action = OnDeleteAction.NO_ACTION)
		@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
		private EducationModel education;
	 
	 @Column(name ="nameedu",nullable = false)
	 private String name;
	 
	 @Column(name ="gpa",nullable = false)
	 private String gpa;
	 
	 @Column(name = "mulai",nullable = false)
	 private Date mulai;
	 
	 @Column(name = "berakhir",nullable = false)
	 private Date berakhir;

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

	public EducationModel getEducation() {
		return education;
	}

	public void setEducation(EducationModel education) {
		this.education = education;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGpa() {
		return gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
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
