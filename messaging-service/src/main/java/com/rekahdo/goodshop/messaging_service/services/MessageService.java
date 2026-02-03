package com.rekahdo.goodshop.messaging_service.services;

import com.rekahdo.goodshop.messaging_service.dtos.request.SendOtpToEmail;
import com.rekahdo.goodshop.messaging_service.dtos.request.SendOtpToNumber;
import com.rekahdo.goodshop.messaging_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.messaging_service.feign.dtos.AppUserClient;
import com.rekahdo.goodshop.messaging_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {

	private final EmailService emailService;
	private final TwilioService twilioService;
	private final UserServiceClient userService;

	public void sendOtpToEmail(SendOtpToEmail request){
		Context context = new Context();
		context.setVariable("title", request.title());
		context.setVariable("otp", request.otp());
		context.setVariable("expireInMinutes", request.expireInMins());

		String subject = String.format("%s - %d", request.title(), request.otp());
		emailService.sendTemplateEmail(request.email(), subject, "otp-email", context);
	}

	public void sendOtpToNumber(SendOtpToNumber request) {
		String sendTo = String.format("+%s%s", request.countryCode(), request.number());
		String text = String.format("%s ---- Your number verification OTP is '%s' and is valid for %d minutes.",
				request.title(), request.otp(), request.expireInMins());

		twilioService.sendSMS(sendTo, text);
	}

	public void sendAcctCreatedEmail(String email) {
		AppUserClient user = userService.returnValidated(email, ApiKey.USER_SERVICE);
		String title = "User account creation successful";

		Context context = new Context();
		context.setVariable("title", title);
		context.setVariable("uid", user.getUid());
		context.setVariable("username", StringUtils.capitalize(user.getUsername()));
		context.setVariable("email", user.getEmail());
		context.setVariable("verified", "ACCOUNT NOT VERIFIED");
		context.setVariable("createdAt", LocalDateTime.now());

		emailService.sendTemplateEmail(user.getEmail(), title, "account-created", context);
	}

	public void sendAccountVerified(String email) {
		AppUserClient user = userService.returnValidated(email, ApiKey.USER_SERVICE);
		String title = "User account verification successful";

		Context context = new Context();
		context.setVariable("title", title);
		context.setVariable("uid", user.getUid());
		context.setVariable("username", StringUtils.capitalize(user.getUsername()));
		context.setVariable("email", user.getEmail());
		context.setVariable("verified", "ACCOUNT VERIFIED");
		context.setVariable("verifiedAt", LocalDateTime.now());

		emailService.sendTemplateEmail(user.getEmail(), title, "account-verified", context);
	}
}