package com.hostmdy.hotelbooking.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.hotelbooking.payload.SimpleEmailRequest;
import com.hostmdy.hotelbooking.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class EmailController {

private final EmailService emailService;
	
	@PostMapping("/contact")
	public ResponseEntity<String> sendEmail(@RequestBody SimpleEmailRequest emiRequest){
		
		emailService.sendEmail(emiRequest);
		
		return ResponseEntity.ok("Email Sent");
	}
	
	

}
