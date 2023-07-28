package com.example.chiffrage.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@PropertySource(value = {"classpath:application.properties"})
public class MailConfiguration {
    @Value("${spring.mail.host}")
    private String serverHost;
    @Value("${spring.mail.port}")
    private Integer serverPort;
    @Value("${spring.mail.username}")
    private String serverUsername;
    @Value("${spring.mail.password}")
    private String serverPassword;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String serverAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String serverTls;

    @Bean
    public JavaMailSender getJavaMailSender()
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(serverHost);
        mailSender.setPort(serverPort);
        mailSender.setUsername(serverUsername);
        mailSender.setPassword(serverPassword);

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", serverHost);
        properties.put("mail.smtp.user", serverUsername);
        properties.put("mail.smtp.port", serverPort);
        properties.put("mail.smtp.auth", serverAuth);
        properties.put("mail.smtp.starttls.enable", serverTls);
        properties.put("mail.smtp.ssl", "true");
        properties.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("This is the test email template for your email:\n%s\n");
        return message;
    }

}
