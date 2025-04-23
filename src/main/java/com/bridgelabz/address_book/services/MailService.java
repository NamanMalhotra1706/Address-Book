package com.bridgelabz.address_book.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String from, String to, String subject, String body) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            log.info("Sending email");
            mailSender.send(message);
            log.info("Email sent");

        }
        catch(Exception e){
            log.error("Email sending failed: {}", e.getMessage());
        }
    }
}
