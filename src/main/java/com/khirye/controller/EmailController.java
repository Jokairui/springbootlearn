package com.khirye.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


/**
 * Created by zhoukairui on 2017/7/3.
 */
@RestController
public class EmailController {
    Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Value("${email.from}")
    private String from;
    @Value("${email.host}")
    private String host;

    @GetMapping(value = "/sendEmail")
    public String sendEmail(){
        String to = "zhoukrui@126.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth",true);
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("Khirye_Jo@163.com","Lvyuxi20");
            }
        });
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject("This is a subject line");
            mimeMessage.setText("this is a text content");
            Transport.send(mimeMessage);
            logger.debug("send email success!");
            return "success";
        }catch(Exception e){
            logger.error("send email Exception:[{}]",e.getLocalizedMessage());
            e.printStackTrace();
        }
        return "failure";
    }
}
