package com.hostmdy.hotelbooking.utility;


import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailConstructor {

	private final Environment env;
	
	public SimpleMailMessage constructSimpleMail(String to,String subject,String text) {
		// TODO Auto-generated method stub
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(env.getProperty("support.mail"));
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(text);
		
		return mail;
	}
	
}
