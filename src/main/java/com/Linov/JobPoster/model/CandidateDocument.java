package com.Linov.JobPoster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "document",uniqueConstraints = @UniqueConstraint(columnNames = {"idcandidate","idtypedoc"}))
public class CandidateDocument {





	public CandidateDocument(CandidateModel candidate, String filename, String type, byte[] pic,
			DocumentTypeModel doctype) {
		super();
		this.candidate = candidate;
		this.filename = filename;
		this.type = type;
		this.pic = pic;
		this.doctype = doctype;
	}



	public CandidateDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "idcandidate", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CandidateModel candidate;
	
	 @Column(name = "filename")
	  private String filename;
	    
	    @Column(name = "type")
	  private String type;
	  
	  @Lob
	    @Column(name="pic")
	    private byte[] pic;
	  
	 
	  
	  
	@ManyToOne(optional = false)
	@JoinColumn(name = "idtypedoc", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private DocumentTypeModel doctype;
	  	 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	

	public DocumentTypeModel getDoctype() {
		return doctype;
	}

	public void setDoctype(DocumentTypeModel doctype) {
		this.doctype = doctype;
	}

	public CandidateModel getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateModel candidate) {
		this.candidate = candidate;
	}


}
