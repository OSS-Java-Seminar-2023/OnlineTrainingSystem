package com.training.OnlineTraining.service;


import jakarta.mail.MessagingException;

import java.util.concurrent.CompletableFuture;

public interface MailService {
    void sendEmail(String toEmail, String body, String subject) throws MessagingException;
    CompletableFuture<Void> sendEmailAsync(String toEmail, String body, String subject) throws MessagingException;
}
