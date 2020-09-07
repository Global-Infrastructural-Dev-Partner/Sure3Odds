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

    public void sendEmail(String email, String userName, String planType) {
            Mail mail = prepareMail(email, userName, planType);
            Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if (response != null) {
                System.out.println("response code from sendgrid " + response.getBody());
                System.out.println("response code from sendgrid " + response.getHeaders());
                System.out.println("response code from sendgrid " + response.getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Mail prepareMail(String email, String userName, String planType) {

        Mail mail = new Mail();
        Email fromEmail = new Email();
        fromEmail.setEmail("support@sure3odds.com");
        fromEmail.setName("Sure3Odds");
        mail.setFrom(fromEmail);

        Email toEmail = new Email();
        toEmail.setEmail(email);
        toEmail.setName(userName);
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("name", userName);
        personalization.addDynamicTemplateData("plantype", planType);
        personalization.addTo(toEmail);
        mail.addPersonalization(personalization);

        Email replyTo = new Email();
        replyTo.setName("Sure3Odds");
        replyTo.setEmail("support@sure3odds.com");
        mail.setReplyTo(replyTo);;

        mail.setTemplateId(templateId);


        return mail;
    }


}
