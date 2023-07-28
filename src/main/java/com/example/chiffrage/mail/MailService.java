package com.example.chiffrage.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("MailService")
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(String to,String subject,String text)
    {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("chamcham.hamoumi@outlook.fr");
            message.setTo(to);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(text);
            javaMailSender.send(message);
        }catch (MailException mailException)
        {
            mailException.printStackTrace();
        }
    }
}
