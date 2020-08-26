package com.gidp.sure3odds.service.users;

import com.gidp.sure3odds.entity.response.BaseResponse;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;


    public BaseResponse sendMail(String toEmail, String subject, String message) {
        BaseResponse response = new BaseResponse();

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailMessage.setFrom("support@sure3odds.com");

        javaMailSender.send(mailMessage);
        response.setData("success");
        response.setDescription("report found succesfully.");
        response.setStatusCode(HttpServletResponse.SC_OK);
        return response;
    }

    public String sendmail() throws AddressException, MessagingException, IOException {
        String me = null;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.sure3odds.com");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("support@sure3odds.com", "@Dee20mene");
            }
        });

        try {
//            Message message = (Message) new MimeMessage(session);
//            message.setFrom((Address) new InternetAddress(from));
//            message.setRecipients(Message.RecipientType.TO, (Address[]) InternetAddress.parse(to));
//            message.setSubject(subject);
//            message.setText(msg);
//            Transport.send(message);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("support@sure3odds.com", false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("visitsaint@gmail.com"));
            msg.setSubject("Tutorials point email");
            msg.setContent("Tutorials point email", "text/html");
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("Tutorials point email", "text/html");
            Transport.send(msg);
            System.out.println("Message send successfully....");
            me = "success";
        } catch (MessagingException e) {
            me = e.getMessage();
            throw new RuntimeException((Throwable) e);
        }
        return me;
    }


    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail.sure3odds.com");
        mailSender.setPort(465);

        mailSender.setUsername("support@sure3odds.com");
        mailSender.setPassword("@Dee20mene");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
