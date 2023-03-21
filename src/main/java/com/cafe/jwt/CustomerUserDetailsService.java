package com.cafe.jwt;

import com.cafe.dao.UserRepository;
import com.cafe.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;





@Configuration

@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {

    User  user;
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          user = userRepository.findByEmail(username);

        log.info("inside userDetail Service");

        if(!Objects.isNull(user))
        {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),

                    new ArrayList<>()


            );
        }else {

            throw  new UsernameNotFoundException("User Not Found");

        }

    }



    public  User  getUserDetail()
    {



        return   user;
    }






}
