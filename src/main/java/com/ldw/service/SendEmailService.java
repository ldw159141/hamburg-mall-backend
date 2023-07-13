package com.ldw.service;

public interface SendEmailService {

    public void sendSimpleEmail(String from,String to,String subject,String text);
}
