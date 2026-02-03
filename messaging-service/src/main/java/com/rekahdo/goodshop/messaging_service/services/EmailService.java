package com.rekahdo.goodshop.messaging_service.services;

import com.rekahdo.goodshop.messaging_service.dtos.request.SimpleEmail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;

@Service
@Setter
@RequiredArgsConstructor
@Slf4j
public class EmailService {

	private final JavaMailSender mailSender;
	private final TemplateEngine templateEngine;

	@Value("${spring.mail.username}")
	private String from;

	@Value("${mail.sender.name}")
	private String senderName;

	public void sendSimpleEmail(SimpleEmail request){
        SimpleMailMessage message = new SimpleMailMessage();

        try {
            InternetAddress fromAddress = new InternetAddress(from, senderName);
			message.setFrom(fromAddress.toString());
        } catch (UnsupportedEncodingException e) {
			message.setFrom(from);
        }

		message.setTo(request.toEmail());
		message.setSubject(request.title());
		message.setText(request.text());

		logSending(request.toEmail(), request.title());
		mailSender.send(message);
		logSent(request.toEmail());
	}

	public void sendTemplateEmail(String sendTo, String subject, String template, Context context) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(sendTo);
			helper.setSubject(subject);
			helper.setFrom(from, senderName);
			helper.setText(templateEngine.process(template, context), true);

			logSending(sendTo, subject);
			mailSender.send(message);
			logSent(sendTo);
		} catch (MessagingException | UnsupportedEncodingException e) {
			log.error("❌ Failed to send email to: {}", sendTo, e);
            throw new RuntimeException(e);
        } catch (Exception e) {
			log.error("❌ Unexpected error sending email to: {}", sendTo, e);
			throw new RuntimeException("Email sending failed", e);
		}
    }

	private void logSending(String to, String subject){
		log.info("Sending email to: {}, Subject: {}", to, subject);
	}

	private void logSent(String to){
		log.info("✅ Email sent successfully to: {}", to);
	}

}