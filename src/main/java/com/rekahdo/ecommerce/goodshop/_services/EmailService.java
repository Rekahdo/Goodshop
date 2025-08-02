package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._dtos.records.MailBody;
import com.rekahdo.ecommerce.goodshop.utilities.ApiLogger;
import com.rekahdo.ecommerce.goodshop.utilities.StringFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	private final ApiLogger logger = new ApiLogger(getClass());

	@Value("${spring.application.name}")
	private String appName;

	@Value("${spring.mail.username}")
	private String from;

	public void sendSimpleMessage(MailBody mailBody){
		SimpleMailMessage massage = new SimpleMailMessage();
		massage.setTo(mailBody.to());
		massage.setFrom(from);
		massage.setSubject(String.format("%s: %s", StringFormat.removeUnderscore(appName.toUpperCase()), mailBody.subject()));
		massage.setText(mailBody.text());

		mailSender.send(massage);
		logger.log(String.format("Mail sent to '%s'", mailBody.to()));
	}

}