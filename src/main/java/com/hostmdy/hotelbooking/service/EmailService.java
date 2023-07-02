package com.hostmdy.hotelbooking.service;

import com.hostmdy.hotelbooking.payload.SimpleEmailRequest;

public interface EmailService {
	
	void sendEmail(SimpleEmailRequest email);
	


}
