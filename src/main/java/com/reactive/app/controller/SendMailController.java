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
		details.setAttachments(null);
		details.setMsgBody("Hi2!!!");
		details.setRecipient("buddhabhushan0414@gmail.com");
		details.setSubject("testing");
		Mono<String> status = emailService.sendSimpleMail(details);
		System.out.println("hehe:" + status);
		return details;
	}

	@PostMapping("/send")
	public EmailDetails sendMailWithAttachment() {
		EmailDetails details = new EmailDetails();
		String[] attachments = { "C:/Users/Bbhushan/Pictures/my passport_size_photo.jpg",
				"C:/Users/Bbhushan/Downloads/BuddhabhushanB_Resume.pdf",
				"C:/Users/Bbhushan/Downloads/Shubham-Ayachit-Resume.pdf" };
		details.setAttachments(attachments);
		details.setMsgBody("Please find attached resume below!!!");
		details.setRecipient("buddhabhushan0414@gmail.com");
		details.setSubject("Sharing Resume from SpringBoot app");
		Mono<String> status = emailService.sendMailWithAttachment(details);
		return details;
	}
}
