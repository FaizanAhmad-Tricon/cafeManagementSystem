package com.cafe.utils;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
public class EmailUtil {


    @Autowired
    JavaMailSender emailSender;



    public  void SendMail(String to , String from, boolean status, List<String> adminList)
    {



        adminList.remove(from);

        SimpleMailMessage  simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);

        System.out.print(from);



        simpleMailMessage.setCc(getCc(adminList));


        if(status)
        {
            simpleMailMessage.setText("Your Account has been Activated By Admin:  "+ from);
            simpleMailMessage.setSubject("Account Approved");

        }
        else
        {
            simpleMailMessage.setText("Your Account has been Deactivated By Admin:  "+ from);
            simpleMailMessage.setSubject("Account Disabled");

        }





        emailSender.send(simpleMailMessage);



    }

    private String[]  getCc( List<String> adminList)
    {

        String[]  cc = new String[adminList.size()];

        for(int i=0;i<adminList.size();i++)
        {
            cc[i]= adminList.get(i);
        }

        return  cc;

    }



    public void sendForgotPassword(String to , String password ) throws MessagingException {

        MimeMessage message =  emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setFrom("faizanahmad9576808817@gmail.com");
        helper.setSubject("forgot password Credenrtial");
        String text =  "<h2> Your Credential</h2> <p>Email :" + to+ "<br><br> password :"+ password +" <br> <br> <a href='http://localhost:8081/user/login'> Click here to login<a></p>";
        message.setContent(text,"text/html");

        emailSender.send(message);

    }
}
