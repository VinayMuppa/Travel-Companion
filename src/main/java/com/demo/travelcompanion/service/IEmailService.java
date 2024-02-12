package com.demo.travelcompanion.service;

public interface IEmailService {
    public void sendEmail(String receiverEmail, String subject, String body);
}
