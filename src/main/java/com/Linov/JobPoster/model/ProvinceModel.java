package com.Linov.JobPoster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "province",uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class ProvinceModel {
	
	public ProvinceModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProvinceModel(String code, String province) {
		super();
		this.code = code;
		this.province = province;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name = "code",nullable = false)
	private String code;
	
	@Column(name ="province",nullable = false)
	private String province;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
