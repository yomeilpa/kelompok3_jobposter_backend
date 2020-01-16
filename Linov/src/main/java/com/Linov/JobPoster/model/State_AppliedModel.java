package com.Linov.JobPoster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "state")
public class State_AppliedModel {
	
	public State_AppliedModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public State_AppliedModel(String code, String state) {
		super();
		this.code = code;
		this.state = state;
	}


	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name = "code",unique = true)
	private String code;
	
	
	@Column(name = "state")
	private String state;


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


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	

}
