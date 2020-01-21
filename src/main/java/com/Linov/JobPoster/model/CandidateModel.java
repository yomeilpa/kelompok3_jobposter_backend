package com.Linov.JobPoster.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "candidate",uniqueConstraints = { @UniqueConstraint(columnNames = {"phonenumber"}),@UniqueConstraint(columnNames = "email")})

public class CandidateModel {
	
	

	public CandidateModel(String gender, String name, Date dob, String addres, String phone, String email,
			String filename, String type, byte[] pic, CityModel city) {
		super();
		this.gender = gender;
		this.name = name;
		this.dob = dob;
		this.addres = addres;
		this.phone = phone;
		this.email = email;
		this.filename = filename;
		this.type = type;
		this.pic = pic;
		this.city = city;
	}

	public CandidateModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	
	@Column(name = "gender",nullable = false)
	private String gender;
	
	
	@Column(name ="name",nullable = false)
	private String name;
	
	@Column(name ="dob",nullable = false)
	private Date dob;
	
	@Column(name = "addres",nullable = false)
	private String addres;
	
	@Column(name = "phonenumber",nullable = false)
	private String phone;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	 @Column(name = "filename")
	  private String filename;
	    
	    @Column(name = "type")
	  private String type;
	  
	  @Lob
	    @Column(name="pic")
	    private byte[] pic;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idcity", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private CityModel city;
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public CityModel getCity() {
		return city;
	}

	public void setCity(CityModel city) {
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
