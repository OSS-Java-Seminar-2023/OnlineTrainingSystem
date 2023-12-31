package com.training.OnlineTraining.service.implementation;


import com.training.OnlineTraining.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    @Async
    @Override
    public CompletableFuture<Void> sendEmailAsync(String toEmail, String body, String subject) throws MessagingException {
        sendEmail(toEmail, body, subject);
        System.out.println("Sent email asynchronously");
        return CompletableFuture.completedFuture(null); // or simply return new CompletableFuture<>();
    }

    @Override
    public void sendEmail(String toEmail, String body, String subject) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("onlinetrainingsystemteam@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        javaMailSender.send(mimeMessage);
    }
}
