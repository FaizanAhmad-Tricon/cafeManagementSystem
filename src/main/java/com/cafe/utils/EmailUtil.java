package com.cafe.utils;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class EmailUtil {


    @Autowired
    JavaMailSender javaMailSender;



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





        javaMailSender.send(simpleMailMessage);



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
}
