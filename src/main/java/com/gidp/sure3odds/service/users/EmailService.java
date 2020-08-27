package com.gidp.sure3odds.service.users;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    SendGrid sendGrid;
    @Value("${sure.sendgrid.template-id}")
    private String templateId;
    @Value("${sure.sendgrid.api-key}")
    private String appKey;


    public String sendEmail(String email) {
            Mail mail = prepareMail(email);
            Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if (response != null) {
                System.out.println("response code from sendgrid " + response.getHeaders());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error in sending email";
        }
        return "mail had been sent check your inbox";

    }


    public Mail prepareMail(String email) {

        Mail mail = new Mail();
        Email fromEmail = new Email();
        fromEmail.setEmail("support@sure3odds.com");
        fromEmail.setName("Sure3Odds");
        Email toEmail = new Email();
        toEmail.setEmail(email);
        toEmail.setName("Saint Saint");
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("name", "Saint Deemene");
        mail.setFrom(fromEmail);
        personalization.addTo(toEmail);
        mail.addPersonalization(personalization);
        mail.setTemplateId(templateId);
        return mail;
    }


}
