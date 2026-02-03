package com.rekahdo.goodshop.phone_service.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Component
public class ApiKey {
    
    public static final String AUTH_SERVICE = "access-to-1030-port";
    public static final String USER_SERVICE = "access-to-1020-port";
    public static final String OTP_SERVICE = "access-to-1040-port";
    public static final String SERVICE_KEY = "access-to-1070-port";
    public static final String VENDOR_SERVICE = "access-to-1120-port";

    public static void validate(String apikey){
        if(apikey == null || !Objects.equals(SERVICE_KEY, apikey))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

}