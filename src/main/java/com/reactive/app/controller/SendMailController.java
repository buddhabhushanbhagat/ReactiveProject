package com.reactive.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.app.entity.EmailDetails;
import com.reactive.app.service.EmailService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("email")
public class SendMailController {
	@Autowired
	private EmailService emailService;

	@GetMapping("/send")
	public EmailDetails sendMail() {
		EmailDetails details = new EmailDetails();
		String[] recipientArray = {"bhagatbuddhabhushan@gmail.com","buddhabhushan0414@gmail.com"};
		details.setAttachments(null);
		details.setMsgBody("Hi2!!!");
		details.setSubject("testing");
		details.setRecipient(recipientArray);

		Mono<String> status = emailService.sendSimpleMail(details);
		System.out.println("hehe:" + status);
		return details;
	}

	@PostMapping("/send")
	public Mono<String> sendMailWithAttachment(@RequestBody EmailDetails emailDetails) {
		
		Mono<String> status = emailService.sendMailWithAttachment(emailDetails);
		return status;
	}
}
