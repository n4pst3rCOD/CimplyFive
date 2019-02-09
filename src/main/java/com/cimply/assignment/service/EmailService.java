package com.cimply.assignment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    @Value("${cimply.email.smtp.server}")
    private String SmtpServer;

    @Value("${cimply.email.sslfactory}")
    private String SSL_FACTORY;

    @Value("${cimply.email.smtp.port}")
    private String serverPort;

    @Value("${cimply.email.protocol}")
    private String emailProtocol;

    @Value("${cimply.email.smtp.transport.protocol}")
    private String transportProtocol;

    @Value("${cimply.email.username}")
    private String username;

    @Value("${cimply.email.password}")
    private String password;

    @Value("${cimply.email.from")
    private String from;


    public void sendEmail(String to, String firstName, String lastName) {

        Properties props = System.getProperties();


        props.setProperty("mail.smtp.host", SmtpServer);
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", serverPort);
        props.setProperty("mail.smtp.socketFactory.port", serverPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", emailProtocol);
        props.put("mail.transport.protocol", transportProtocol);


        Session session = Session.getDefaultInstance(props,
                new Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username.trim(), password.trim());
                    }});

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Registration successful @ CimplyFive");
            message.setText("Hey " + firstName + " " + lastName + "!!  You have been registered.");
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
