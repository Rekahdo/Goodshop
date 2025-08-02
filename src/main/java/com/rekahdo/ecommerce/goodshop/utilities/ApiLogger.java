package com.rekahdo.ecommerce.goodshop.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class ApiLogger {

    private final Logger logger;

    public ApiLogger(Class<?> callingClass) {
        this.logger = LoggerFactory.getLogger(callingClass);
    }

    public void log(String message){
        logger.info("LOG <<< {} >>> TIME = {}", message, Instant.now().toString());
    }

}
