package com.Linov.JobPoster.Validasi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.JobKategoriModel;
import com.Linov.JobPoster.model.JobPosition;
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
	
	
	
	
}
