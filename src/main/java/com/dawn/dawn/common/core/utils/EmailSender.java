package com.dawn.dawn.common.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * @author chenliming
 * @date 2023/12/27 22:21
 */

@Component
public class EmailSender {
    @Autowired
    private MailSender mailSender;
    @Value("${spring.mail.username}")
    private String form;
    /**
     * 发送邮件
     * @param to 收件人
     * @param subject 标题
     * @param body 发送内容
     */
    public void sendEmail(String to,String subject,String body){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(form);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        try {
            mailSender.send(simpleMailMessage);
        }catch (MailException e){
            e.printStackTrace();
        }
    }
}
