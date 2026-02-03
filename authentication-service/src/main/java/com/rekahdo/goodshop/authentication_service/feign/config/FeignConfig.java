package com.rekahdo.goodshop.authentication_service.feign.config;//package com.rekahdo.goodshop.authentication_service.feign.config;
//
//import com.rekahdo.goodshop.authentication_service.security.jjwt.JwtTokenService;
//import feign.RequestInterceptor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FeignConfig {
//
//    @Value("${X-API-KEY}")
//    private String apiKey;
//
//    @Value("${application.account.username}")
//    private String username;
//
//    @Value("${application.account.role}")
//    private String role;
//
//    @Bean
//    public RequestInterceptor serviceAuthInterceptor(JwtTokenService tokenService) {
//        String token = tokenService.generateToken(username, role).token();
//        System.out.println("Intercept: " + token);
//        return requestTemplate ->
//                requestTemplate.header("Authorization", "Bearer " + token);
//    }
//
//}