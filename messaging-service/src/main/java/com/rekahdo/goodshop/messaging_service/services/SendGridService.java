package com.rekahdo.goodshop.messaging_service.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridService {
    
    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;
    
    public void sendEmail(String to, String subject, String content) {
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        
        try {
            Email from = new Email("no-reply@goodshop.com", "Goodshop");
            Email toEmail = new Email(to);
            Content emailContent = new Content("text/html", content);
            Mail mail = new Mail(from, subject, toEmail, emailContent);
            
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw new RuntimeException("SendGrid error", ex);
        }
    }
}