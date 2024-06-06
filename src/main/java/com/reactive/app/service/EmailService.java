package com.reactive.app.service;

import com.reactive.app.entity.EmailDetails;

import reactor.core.publisher.Mono;

public interface EmailService {
	Mono<String> sendSimpleMail(EmailDetails details);

	// Method
	// To send an email with attachment
	Mono<String> sendMailWithAttachment(EmailDetails details);
}
