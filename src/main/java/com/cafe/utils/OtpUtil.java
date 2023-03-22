package com.cafe.utils;


import com.cafe.dao.UserRepository;
import com.cafe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service

public class OtpUtil {


    @Autowired
    UserRepository userRepository;


    public String generateOtp(String email) {

        String values = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz0123456789";


        String opt = "";


        for (int i = 0; i < 4; i++) {

            int index = ThreadLocalRandom.current().nextInt(1, values.length());
            opt += values.charAt(index);
        }


        return opt;


    }


    public boolean verifyOtp(String otp, String email) {

        User user = userRepository.findByEmail(email);


         Date otptoBeExpired = new Date(user.getOtpGenerationTime().getTime()+(10 * 60 * 1000));


        return user.getOneTimePassword().equals(otp) &&  otptoBeExpired.before(new Date()) ? true : false;


    }


}
