package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop.utilities.ApiLogger;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OneTimePasswordService {

	private final ApiLogger apiLogger = new ApiLogger(getClass());

	public Integer generate() {
		Random random = new Random();
		apiLogger.log("OTP generated");
		return random.nextInt(100_000, 999_999);
	}

}