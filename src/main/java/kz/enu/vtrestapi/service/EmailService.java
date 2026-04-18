package kz.enu.vtrestapi.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String senderEmail;

    public EmailService(JavaMailSender mailSender,
                        @Value("${spring.mail.username:}") String senderEmail) {
        this.mailSender = mailSender;
        this.senderEmail = senderEmail;
    }

    public void sendEmail(String to, String subject, String text) {
        if (!StringUtils.hasText(to)) {
            throw new IllegalArgumentException("Recipient email is required");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        if (StringUtils.hasText(senderEmail)) {
            message.setFrom(senderEmail);
        }
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
