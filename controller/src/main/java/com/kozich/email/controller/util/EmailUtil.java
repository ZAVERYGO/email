package com.kozich.email.controller.util;

import com.kozich.email.service.api.dto.MessageDto;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailUtil {
    private static final String SENDER_EMAIL = "Kozichnick@mail.ru";
    private static final String EMAIL_PASSWORD = System.getenv("email_password");

    private static final Properties PROPERTIES;

    static {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "smtp.mail.ru");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.user", SENDER_EMAIL);
        properties.put("mail.smtp.password", EMAIL_PASSWORD);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");

        PROPERTIES = properties;
    }

    public static void sendMessage(MessageDto messageDto){
        Session session = Session.getInstance(PROPERTIES, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, EMAIL_PASSWORD);
            }});
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(messageDto.getRecipientEmail()));
            message.setSubject(messageDto.getTopic());
            message.setText(messageDto.getText());
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
