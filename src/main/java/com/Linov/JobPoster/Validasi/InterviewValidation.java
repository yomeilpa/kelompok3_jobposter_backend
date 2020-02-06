package com.Linov.JobPoster.Validasi;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.ListofInterviewModel;

@Service
public class InterviewValidation {
	
	
	public void validasiidnull(ListofInterviewModel inter) throws Exception{
		if(inter.getId() == null) {
			throw new Exception("Id Must Null");
		}
	}
	
	public void validasiidnunotll(ListofInterviewModel inter) throws Exception{
		if(inter.getId() != null) {
			throw new Exception("Id Null");
		}
	}
	
	public void validasiNotFk(ListofInterviewModel inter) throws Exception {
		if(inter.getDate() == null) {
			throw new Exception("Date Interview Must Be Filled");
		}
		Date date = new Date();
		if(date.compareTo(inter.getDate()) > 0) {
			throw new  Exception("Interview Date Must Be Higher over Date now");
		}
		if(inter.getJob() == null) {
			throw new Exception("Job Apply Must Be Filled");
		}
		if(inter.getTime() == null) {
			throw new Exception("Time Must Be Filled");
		}
	}

}
