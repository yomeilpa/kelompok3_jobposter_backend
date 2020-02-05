package com.Linov.JobPoster.service;

import java.util.Date;
import com.Linov.JobPoster.model.ContractModel;
import com.Linov.JobPoster.model.JobApplyModel;
import com.Linov.JobPoster.model.ListofInterviewModel;
import com.Linov.JobPoster.model.Mail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;



@Service
public class EmailService {
	//private final String subject = "Password for Your Account in Linov JobPoster";
	
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	@Qualifier("emailConfigBean")
	private Configuration emailConfig;
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
		
	}
	
	
	public void sendNotid(String to,String password,String username) throws MessagingException, IOException, TemplateException {
		
		Mail mail = new Mail();
        Map<String, String> model = new HashMap<String, String>();
        model.put("username",username);
        model.put("password", password);
        mail.setModel(model); 
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.addInline("logo.png", new ClassPathResource("classpath:/lwcn-logo.jpeg"));

        Template template = emailConfig.getTemplate("sendPassword.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject("Password for Linov HR");
        mimeMessageHelper.setFrom("no-reply@gmail.com");


        javaMailSender.send(message);

    }

	
	public void sendContract(JobApplyModel eg,ContractModel ct) throws Exception {
		SimpleMailMessage mail = new SimpleMailMessage();
		Date date = ct.getDate();
		mail.setTo(eg.getCandidate().getEmail());
		mail.setSubject("Contract Invitation");
		mail.setText("Hello,"+ eg.getCandidate().getName()+ " \n"
				+"\n Congrulations,"+ "We Invited you to attend on Contract Negoatiation for \n "+eg.getJob().getTitle()+" Postion at :  \n"
				+"Date : "+date+"\n"+
				"Time  : "+ct.getTime()+"\n\n\n"+"Best Regards, "+eg.getJob().getCandidate().getName());	
		javaMailSender.send(mail);
	}
	
	public void sendInvitation(ListofInterviewModel eg) throws Exception {
		SimpleMailMessage mail = new SimpleMailMessage();
		Date date = eg.getDate();
		mail.setTo(eg.getJob().getCandidate().getEmail());
		mail.setSubject("Interview Invitation");
		mail.setText("Hello,"+ eg.getJob().getCandidate().getName()+ " \n"
				+ "We Invited you to attend on interview \n "+eg.getJob().getJob().getTitle()+" Postion at :  \n"
				+"Date : "+date+"\n"+"/n Lokasi : "+eg.getJob().getJob()+
				"Time  : "+eg.getTime()+"\n\n\n"+"Best Regards, "+eg.getJob().getJob().getCandidate().getName());	
		javaMailSender.send(mail);
	}
	
//	public void sendInvitation(ListofInterviewModel eg) throws MessagingException, IOException, TemplateException {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MMMM-dd");
//		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
//	    String strDate = dateFormat.format(eg.getDate());
//		String strTime = timeFormat.format(eg.getTime());
//		Mail mail = new Mail();
//        Map<String, String> model = new HashMap<String, String>();
//        model.put("name",eg.getJob().getCandidate().getName());
//        model.put("lokasi",eg.getJob().getJob().getAddres());
//        model.put("date", strDate);
//        model.put("time", strTime);
//        model.put("position", eg.getJob().getJob().getTitle());
//        model.put("recruiter",eg.getJob().getJob().getCandidate().getName());
//        mail.setModel(model); 
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//        mimeMessageHelper.addInline("logo.png", new ClassPathResource("classpath:/lwcn-logo.jpeg"));
//
//        Template template = emailConfig.getTemplate("invitationInterview.ftl");
//        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
//        mimeMessageHelper.setTo(eg.getJob().getCandidate().getEmail());
//        mimeMessageHelper.setText(html, true);
//        mimeMessageHelper.setSubject("Invitation for Linov HR");
//        mimeMessageHelper.setFrom("no-reply@gmail.com");
//        javaMailSender.send(message);
//    }	
	public void sendInvReject(ListofInterviewModel eg) throws Exception {
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
	
	public void sendReschedule(ListofInterviewModel eg) throws Exception {
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
	
	public void sendAcc(ListofInterviewModel eg) throws Exception {
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
	
	public void sendResult(ListofInterviewModel eg) throws Exception {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(eg.getJob().getCandidate().getEmail());
		mail.setSubject("Result for Your Invitattion : ");
		mail.setText("Hello,"+ eg.getJob().getCandidate().getName()+ " \n"
				+ "This is a result for your interview for " +": \n "+eg.getJob().getJob().getTitle()+" Postion at :  \n"
				+"Result :" +""+ eg.getResultInt()+"\n"+
				"\n\n"+"\n\n\n\n\n"+"Best Regards :"+eg.getJob().getJob().getCandidate().getName());	
		javaMailSender.send(mail);
	}
	
	

}
