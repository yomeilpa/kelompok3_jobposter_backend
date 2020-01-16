package com.Linov.JobPoster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "candidate_skill")
public class CandidateSkillModel {

	public CandidateSkillModel(CandidateModel candidate, SkillLevelModel level, String skillname) {
		super();
		this.candidate = candidate;
		this.level = level;
		this.skillname = skillname;
	}

	public CandidateSkillModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idcandidate", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CandidateModel candidate;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idskillevel", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private SkillLevelModel level;
	
	@Column(name = "skil_name")
	private String skillname;

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

	public SkillLevelModel getLevel() {
		return level;
	}

	public void setLevel(SkillLevelModel level) {
		this.level = level;
	}

	public String getSkillname() {
		return skillname;
	}

	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}

}
