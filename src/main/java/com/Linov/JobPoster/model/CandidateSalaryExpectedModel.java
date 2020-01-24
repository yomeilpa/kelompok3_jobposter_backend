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
@Table(name = "salary_expected",uniqueConstraints = {
		@UniqueConstraint(columnNames = {"idcandidate"})
})
public class CandidateSalaryExpectedModel {
	
	
	public CandidateSalaryExpectedModel(CandidateModel candidate, Double salary) {
		super();
		this.candidate = candidate;
		this.salary = salary;
	}

	public CandidateSalaryExpectedModel() {
		super();
		// TODO Auto-generated constructor stub
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
	
	@Column(name = "salary")
	private Double salary;

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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}


	
}
