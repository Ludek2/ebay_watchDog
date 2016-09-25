package com.ebay_watchDog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class Mail {
	@Autowired
    private JavaMailSender mailSender;
	
	public void sendSimpleMail(String addressTo, String subject, String text){
		SimpleMailMessage email = new SimpleMailMessage();
	    email.setTo(addressTo);
	    email.setSubject(subject);
	    email.setText(text);
	    try{
	    mailSender.send(email);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}
}
