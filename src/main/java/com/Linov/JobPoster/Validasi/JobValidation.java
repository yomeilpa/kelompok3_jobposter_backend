package com.Linov.JobPoster.Validasi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.JobKategoriModel;
import com.Linov.JobPoster.model.JobPosition;
import com.Linov.JobPoster.model.JobPostingModel;
import com.Linov.JobPoster.service.JobPositionService;
import com.Linov.JobPoster.service.JobPostingService;
import com.Linov.JobPoster.service.JobkategoriService;

@Service
public class JobValidation {
	
	@Autowired
	private JobPositionService position;
	@Autowired
	private JobkategoriService kategori;
	@Autowired
	private JobPostingService posting;
	
	
	public void validIdPositionnull(JobPosition job)throws Exception{
		if(job.getId() != null) {
			throw new Exception("ID Must Null");
		}
	}
	
	public void validIdPositionnotNull(JobPosition job)throws Exception{
		if(job.getId() == null) {
			throw new Exception("ID Null");
		}
	}
	
	public void positionDoesnotExists(JobPosition job) throws Exception{
		if(position.findById(job.getId()) == null) {
			throw new Exception("Position Does Not Exists");
		}
	}
	
	public void validasiFKPosition(JobPosition job) throws Exception{
		if(job.getJobkategori() == null) {
			throw new Exception("Job Kategori Must Be Filled");
		}
		if(job.getName() == null) {
			throw new Exception("Name of Position Must Be Filled");
		}
	}
	
	public void validIdKategorinull(JobKategoriModel job)throws Exception{
		if(job.getId() != null) {
			throw new Exception("ID Must Null");
		}
	}
	
	public void validasiKode(JobKategoriModel job) throws Exception {
		JobKategoriModel jk =kategori.findByCode(job.getCode());
		if(jk != null) {
			throw new Exception("Code is Registered");
		}
		
	}
	
	public void validIdKategorinotNull(JobKategoriModel job)throws Exception{
		if(job.getId() == null) {
			throw new Exception("ID Null");
		}
	}
	
	public void kategoriDoesnotExists(JobKategoriModel job) throws Exception{
		if(kategori.findById(job.getId()) == null) {
			throw new Exception("Kategori Does Not Exists");
		}
	}
	
	public void validasiFKkategori(JobKategoriModel job) throws Exception{
		if(job.getCode() == null) {
			throw new Exception("Code Must Be Filled");
		}
		if(job.getKategori() == null) {
			throw new Exception("Name of Kategori Must Be Filled");
		}
	}
	
	public void validasiPostingidnull(JobPostingModel job) throws Exception{
		if(job.getId() != null) {
			throw new Exception("Id Must Null");
		}
	}
	public void validasiPostingidnotnull(JobPostingModel job) throws Exception{
		if(job.getId() == null) {
			throw new Exception("Id Null");
		}
	}
	public void validasinotFKPosting(JobPostingModel job) throws Exception{
		if(job.getStart() == null) {
			throw new Exception("Start Job Posting Must Be Filled");
		}
		Date date= new Date();
		if(date.compareTo(job.getStart()) > 0) {
			throw new Exception("Date Start must be higher over date now");
		}
		if(job.getEnd() == null) {
			throw new Exception("End Date must be filled");
		}
		if(job.getEnd().compareTo(job.getStart()) < 0) {
			throw new Exception("End date must be lover over Start date ");
		}
		if(job.getSalary() == null) {
			throw new Exception("Salary Must Be Filled");
		}
		if(job.getCandidate() == null) {
			throw new Exception("Candidate Must Be Filled");
		}
		if(job.getCity() == null) {
			throw new Exception("City Must Be Filled");
		}
		if(job.getJobposotion() == null) {
			throw new Exception("Position Must Be Filled");
		}
		if(job.getDesc()== null) {
			throw new Exception("Description Must Be Filled");
		}
		if(job.getSaldo() == null) {
			throw new Exception("Saldo Must Be Filled");
		}
		if(job.getAddres() == null) {
			throw new Exception("Address Must Be Filled");
		}
		
	}
	
	
	
	
}
