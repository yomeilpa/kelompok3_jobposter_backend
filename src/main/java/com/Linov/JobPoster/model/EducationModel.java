package com.Linov.JobPoster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "education",uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class EducationModel {
	
	

	public EducationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EducationModel(String code, String education) {
		super();
		this.code = code;
		this.education = education;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name = "code",nullable = false)
	private String code;
	
	@Column(name = "education",nullable = false)
	private String education;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	

}
