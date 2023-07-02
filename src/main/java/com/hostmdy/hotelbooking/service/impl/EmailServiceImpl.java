package com.hostmdy.hotelbooking.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hostmdy.hotelbooking.payload.SimpleEmailRequest;
import com.hostmdy.hotelbooking.service.EmailService;
import com.hostmdy.hotelbooking.utility.MailConstructor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	
	private final JavaMailSender sender;
	private final MailConstructor mailConstructor;

	@Override
	public void sendEmail(SimpleEmailRequest email) {
		// TODO Auto-generated method stub
		SimpleMailMessage mail = mailConstructor.constructSimpleMail(email.getTo(), email.getSubject(), email.getText());

//		mail.setFrom("htethtet2320@gmail.com");
//		mail.setTo(email.getTo());
//		mail.setSubject(email.getSubject());
//		mail.setText(email.getText());
//		
		sender.send(mail);
	}
	
	

}
