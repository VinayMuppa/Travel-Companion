package com.demo.travelcompanion.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendEmail(String receiverEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("muppavinay@gmail.com");
        message.setTo(receiverEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

    }

}

