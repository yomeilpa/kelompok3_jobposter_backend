package com.Linov.JobPoster.model;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "job_posting")
public class JobPostingModel {
	
	

	public JobPostingModel(JobPosition jobposotion, CandidateModel candidate, String title, Date start, CityModel city,
			Date end, Double salary, Boolean active) {
		super();
		this.jobposotion = jobposotion;
		this.candidate = candidate;
		this.title = title;
		this.start = start;
		this.city = city;
		this.end = end;
		this.salary = salary;
		this.active = active;
	}

	public JobPostingModel() {
		// TODO Auto-generated constructor stub
	}
	
	@Column(name ="addres")
	private String addres;
	
	

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idjobposition", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private JobPosition jobposotion;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idcandidate", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CandidateModel candidate;
	
	@Column(name = "title",nullable = false)
	private String title;
	
	@Column(name = "start_date")
	private Date start;
	
	@Column(name = "job_desc",columnDefinition = "TEXT")
	private String desc;
	
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idcity", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private CityModel city;
	
	@Column(name = "end_date",nullable = false)
	private Date end;
	
	@Column(name = "salary",nullable = false)
	private Double salary;
	
	@Column(name = "active_state",nullable = false)
	private Boolean active;
	
	
	@Column(name = "saldo")
	private Double saldo;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JobPosition getJobposotion() {
		return jobposotion;
	}

	public void setJobposotion(JobPosition jobposotion) {
		this.jobposotion = jobposotion;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public CandidateModel getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateModel candidate) {
		this.candidate = candidate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public CityModel getCity() {
		return city;
	}

	public void setCity(CityModel city) {
		this.city = city;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}
	
}
