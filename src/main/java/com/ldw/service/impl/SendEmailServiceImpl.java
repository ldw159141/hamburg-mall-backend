package com.ldw.service.impl;

import com.ldw.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * @author HP刘德伟
 */
@Service
public class SendEmailServiceImpl implements SendEmailService {


    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 发送纯文本邮件
     * @param from
     * @param to
     * @param subject
     * @param text
     */
    @Override
    public void sendSimpleEmail(String from, String to, String subject, String text) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setSubject(subject);
        mailMessage.setTo(to);
        mailMessage.setText(text);
        try {
            mailSender.send(mailMessage);
            System.out.println("邮件发送成功！");
        }catch (MailException e){
            e.printStackTrace();
            System.out.println("邮件发送失败！");
        }


    }
}
