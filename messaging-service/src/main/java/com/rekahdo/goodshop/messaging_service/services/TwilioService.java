package com.rekahdo.goodshop.messaging_service.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TwilioService {

	@Value("${twilio.account.sid}")
	private String ACCOUNT_SID;

	@Value("${twilio.auth.token}")
	private String AUTH_TOKEN;

	@Value("${twilio.phone.number}")
	private String FROM_NUMBER;

	public void sendSMS(String number, String text) {
		try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			Message.creator(
					new PhoneNumber(number), // To number
					new PhoneNumber(FROM_NUMBER), // From Twilio number
					text
			).create();

		}catch (Exception exception){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

}