package com.rekahdo.goodshop.forgot_password.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class ApiKey {
    
    public static final String AUTH_SERVICE = "access-to-1030-port";
    public static final String USER_SERVICE = "access-to-1020-port";
    public static final String OTP_SERVICE = "access-to-1040-port";
    public static final String SERVICE_KEY = "access-to-1100-port";

    public static void validate(String apikey){
        if(apikey == null || !Objects.equals(SERVICE_KEY, apikey))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

}