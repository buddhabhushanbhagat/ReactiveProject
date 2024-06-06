package com.reactive.app.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.reactive.app.entity.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import reactor.core.publisher.Mono;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	// Method 1
	// To send a simple email
	public Mono<String> sendSimpleMail(EmailDetails details) {

		// Try block to check for exceptions
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			// Sending the mail
			javaMailSender.send(mailMessage);
			return Mono.just("Mail Sent Successfully...");
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return Mono.just("Error while Sending Mail");
		}
	}

//	// Method 2
//	// To send an email with attachment
//	public Mono<String> sendMailWithAttachment(EmailDetails details) {
//		// Creating a mime message
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper mimeMessageHelper;
//
//		try {
//
//			// Setting multipart as true for attachments to
//			// be send
//			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//			mimeMessageHelper.setFrom(sender);
//			mimeMessageHelper.setTo(details.getRecipient());
//			mimeMessageHelper.setText(details.getMsgBody());
//			mimeMessageHelper.setSubject(details.getSubject());
//
//			// Adding the attachment
//			FileSystemResource file = new FileSystemResource(new File(details.getAttachments()[0]));
//
//			mimeMessageHelper.addAttachment(file.getFilename(), file);
//
//			// Sending the mail
//			javaMailSender.send(mimeMessage);
//			return Mono.just("Mail sent Successfully");
//		}
//
//		// Catch block to handle MessagingException
//		catch (MessagingException e) {
//
//			// Display message when exception occurred
//			return Mono.just("Error while sending mail!!!");
//		}
//	}

	public Mono<String> sendMailWithAttachment(EmailDetails details) {
		// Creating a mime message
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		File[] attachments = new File[details.getAttachments().length];
		for (int i = 0; i < details.getAttachments().length; i++) {
			attachments[i] = new File(details.getAttachments()[i]);
		}
		try {

			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(details.getSubject());

			// Adding the attachment
			for (int i = 0; i < details.getAttachments().length; i++) {
				FileSystemResource file = new FileSystemResource(new File(details.getAttachments()[i]));
				mimeMessageHelper.addAttachment(file.getFilename(), file);
			}

			
			// Sending the mail
			javaMailSender.send(mimeMessage);
			return Mono.just("Mail sent Successfully");
		}

		// Catch block to handle MessagingException
		catch (MessagingException e) {

			// Display message when exception occurred
			return Mono.just("Error while sending mail!!!");
		}
	}
}
