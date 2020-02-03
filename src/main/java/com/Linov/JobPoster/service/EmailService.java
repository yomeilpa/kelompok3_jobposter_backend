package com.Linov.JobPoster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.Linov.JobPoster.model.ListofInterviewModel;


@Service
public class EmailService {
	private JavaMailSender javaMailSender;
	private final String subject = "Password for Your Account in Linov JobPoster";
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
		
	}
	public void sendNotid(String to,String password,String username) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText("Your username is : "+username+" \n"
				+ "Your Password is : "+password);	
		javaMailSender.send(mail);
	}
	
	public void sendInvitation(ListofInterviewModel eg) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		String date = eg.getDate().toString();
		date.replaceAll("[0-9]", "");
		mail.setTo(eg.getJob().getCandidate().getEmail());
		mail.setSubject("Interview Invitation");
		mail.setText("Hello,"+ eg.getJob().getCandidate().getName()+ " \n"
				+ "We Invited you to attend on interview \n "+eg.getJob().getJob().getTitle()+" Postion at :  \n"
				+"Date : "+date+"\n"+
				"Time  : "+eg.getTime());	
		javaMailSender.send(mail);
	}

}
