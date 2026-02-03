package com.rekahdo.goodshop.user_service.security.jjwt;//package com.rekahdo.goodshop.user_service.security.jjwt;
//
//import com.rekahdo.goodshop.user_service.feign.clients.AuthenticationServiceClient;
//import com.rekahdo.goodshop.user_service.utilities.ApiKey;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//@Component
//public class JwtFeignInterceptor implements RequestInterceptor {
//
//    @Autowired
//    private AuthenticationServiceClient authenticationService;
//
//    @Override
//    public void apply(RequestTemplate template) {
//        ServletRequestAttributes attributes =
//            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//        if (attributes != null) {
//            String token = attributes.getRequest().getHeader("Authorization");
//
//            if(token == null || token.isEmpty())
//                token = String.format("Bearer %s", authenticationService.generateTokenForService(ApiKey.AUTH_SERVICE));
//
//            template.header("Authorization", token);
//        }
//    }
//
//    private void showRequestInfo(RequestTemplate template){
//        // Print endpoint information
//        System.out.println("=== Feign Client Call ===");
//        System.out.println("HTTP Method: " + template.method());
//        System.out.println("URL: " + template.url());
//        System.out.println("Full URL: " + template.feignTarget().url() + template.path());
//        System.out.println("Headers being sent: " + template.headers());
//        System.out.println("Query Params: " + template.queries());
//        System.out.println("=== End Feign Call ===");
//    }
//}