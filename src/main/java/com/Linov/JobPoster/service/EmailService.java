package com.Linov.JobPoster.service;

import java.util.Date;

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
		Date date = eg.getDate();
		mail.setTo(eg.getJob().getCandidate().getEmail());
		mail.setSubject("Interview Invitation");
		mail.setText("Hello,"+ eg.getJob().getCandidate().getName()+ " \n"
				+ "We Invited you to attend on interview \n "+eg.getJob().getJob().getTitle()+" Postion at :  \n"
				+"Date : "+date+"\n"+
				"Time  : "+eg.getTime()+"\n\n\n"+"Best Regards, "+eg.getJob().getJob().getCandidate().getName());	
		javaMailSender.send(mail);
	}
	
	
	public void sendInvReject(ListofInterviewModel eg) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		Date date = eg.getDate();
		mail.setTo(eg.getJob().getJob().getCandidate().getEmail());
		mail.setSubject("Rejected Invitation");
		mail.setText("Hello,"+ eg.getJob().getJob().getCandidate().getName()+ " \n"
				+ "I'm sorry I did not plan to attend to meet the interview invitation for : \n "+eg.getJob().getJob().getTitle()+" Postion at :  \n"
				+"Date : "+date+"\n"+
				"Time  : "+eg.getTime()+"\n\n\n\n\n"+"Best Regards :"+eg.getJob().getCandidate().getName());	
		javaMailSender.send(mail);
	}
	
	public void sendReschedule(ListofInterviewModel eg) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		Date date = eg.getDate();
		mail.setTo(eg.getJob().getJob().getCandidate().getEmail());
		mail.setSubject("Request New Schedule Invitation");
		mail.setText("Hello,"+ eg.getJob().getJob().getCandidate().getName()+ " \n"
				+ "Sorry I could not be present to fulfill the interview call for : \n "+eg.getJob().getJob().getTitle()+" Postion at :  \n"
				+"Date : "+date+"\n"+
				"Time  : "+eg.getTime()+"\n\n"+"Can i request a Reschedule ?"+"\n\n\n\n\n"+"Best Regards :"+eg.getJob().getCandidate().getName());	
		javaMailSender.send(mail);
	}
	
	public void sendAcc(ListofInterviewModel eg) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		Date date = eg.getDate();
		mail.setTo(eg.getJob().getJob().getCandidate().getEmail());
		mail.setSubject("Accepted Invitation");
		mail.setText("Hello,"+ eg.getJob().getJob().getCandidate().getName()+ " \n"
				+ "I will be Attend the invitation  for : \n "+eg.getJob().getJob().getTitle()+" Postion at :  \n"
				+"Date : "+date+"\n"+
				"Time  : "+eg.getTime()+"\n\n"+"\n\n\n\n\n"+"Best Regards :"+eg.getJob().getCandidate().getName());	
		javaMailSender.send(mail);
	}

}
