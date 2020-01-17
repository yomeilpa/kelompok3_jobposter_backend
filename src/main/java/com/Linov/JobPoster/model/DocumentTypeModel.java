package com.Linov.JobPoster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "document_type")
public class DocumentTypeModel {
	
	public DocumentTypeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocumentTypeModel(String code, String typename, Boolean flag) {
		super();
		this.code = code;
		this.typename = typename;
		this.flag = flag;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name = "code",nullable = false)
	private String code;
	
	@Column(name = "typename",nullable = false)
	private String typename;
	
	@Column(name = "flag",nullable = false)
	private Boolean flag;

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

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	
}
