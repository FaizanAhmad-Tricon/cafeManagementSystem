package com.cafe.utils;


import com.cafe.dao.UserRepository;
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

    @Autowired
    UserRepository userRepository;



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



    public void sendOtpToEmail(String to , String otp ) throws MessagingException {




        String userName = userRepository.findByEmail(to).getName();
        MimeMessage message =  emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setFrom("faizanahmad9576808817@gmail.com");
        helper.setSubject("forgot password Credential");
        String text ="<h3>OTP</h3> <p> Hi </p>  "+userName+",<br><p>you have initiated OTP , Please use it within 5 minutes otherwise it will expire</p>"+
                "Your OTP   :  "+ otp;
         message.setContent(text,"text/html");

        emailSender.send(message);

    }
}
