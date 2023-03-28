package com.cafe.service;


import com.cafe.constent.CafeConstents;
import com.cafe.dao.UserRepository;
import com.cafe.entity.User;
import com.cafe.jwt.*;
import com.cafe.mapper.UserMapperImp;
import com.cafe.utils.CafeUtils;
import com.cafe.utils.EmailUtil;
import com.cafe.utils.OtpUtil;
import com.cafe.wrapper.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.*;


@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    GetPasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmailUtil emailUtil;


    @Autowired
    JwtFilter jwtFilter;


    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    OtpUtil otpUtil;

    //Signup
    public ResponseEntity<String> signUp(SignUpRequest requestMap) {


        try {


            User user = userRepository.findByEmail(requestMap.getEmail());
            if (Objects.isNull(user)) {

                userRepository.save(getUser(requestMap));

                return CafeUtils.getResponseEntity("Successfully Registered", HttpStatus.OK
                );


            } else {
                return CafeUtils.getResponseEntity("Email already Exist", HttpStatus.BAD_REQUEST);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }


//    private boolean ValidateSignUpMap(Map<String, String> requestMap) {
//
//
//        return requestMap.containsKey("name") && requestMap.containsKey("email")
//                && requestMap.containsKey("password") && requestMap.containsKey("contactNumber")
//                ? true : false;
//
//
//    }


    private User getUser(SignUpRequest requestMap) {
        User user = new User();
        user.setName(requestMap.getName());
        user.setEmail(requestMap.getEmail());
        user.setRole("USER");
        user.setPassword(passwordEncoder.getPasswordEncoder().encode(requestMap.getPassword()));
        user.setContactNumber(requestMap.getContactNumber());
        user.setStatus("false");

        return user;
    }


//signUp End


    //login
    public ResponseEntity<?> login(LoginRequest requestMap) {


        try {
            Authentication auth = securityConfig.authenticationManagerBean().authenticate(new UsernamePasswordAuthenticationToken(requestMap.getEmail(), requestMap.getPassword()));
            if (auth.isAuthenticated()) {

                System.out.print(customerUserDetailsService.getUserDetail().getStatus());

                if (customerUserDetailsService.getUserDetail().getStatus().equals("true")) {


                    return new ResponseEntity<>(
                            " {\"token\":\"" + jwtUtil.generateToken(customerUserDetailsService.
                                            getUserDetail().getEmail()
                                    , customerUserDetailsService.
                                            getUserDetail().getRole()) + "\"}", HttpStatus.OK


                    );
                } else {

                    return new ResponseEntity<>("{\" message \"" + "Wait for admin approval" + "\"}",
                            HttpStatus.BAD_REQUEST);

                }


            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity("{\"message\"" + "Bad Request" + "\"}", HttpStatus.BAD_REQUEST);

    }


    //getAllUser service method
    public ResponseEntity<?> getAllUsers() {

        try {

            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(

                        new UserMapperImp().toUserResponseList(userRepository.findAll())


                        , HttpStatus.OK);
            } else {
                return CafeUtils.getResponseEntity(CafeConstents.UN_AUTHORIZED_ACCESS, HttpStatus.FORBIDDEN);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }


    public ResponseEntity<?> updateStatus(String status, int id) {
        try {

            if (jwtFilter.isAdmin()) {
                Optional<User> existUser = userRepository.findById(id);

                if (existUser.isPresent()) {


                    existUser.get().setStatus(status);


                    User updatedUser = userRepository.save(existUser.get());
                    if (updatedUser.getStatus().equalsIgnoreCase("true")) {

                        emailUtil.SendMail(existUser.get().getEmail(), jwtFilter.getCurrentUser(), true, getAllAdmin());


                        return CafeUtils.getResponseEntity("Your Account is Activated by  " + jwtFilter.getCurrentUser(), HttpStatus.OK);


                    } else {

                        emailUtil.SendMail(existUser.get().getEmail(), jwtFilter.getCurrentUser(), false, getAllAdmin());

                        return CafeUtils.getResponseEntity("Your Account is deActivated by   " + jwtFilter.getCurrentUser(), HttpStatus.OK);

                    }


                }

            } else {

                return CafeUtils.getResponseEntity(CafeConstents.UN_AUTHORIZED_ACCESS, HttpStatus.BAD_REQUEST);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private List<String> getAllAdmin() {


        List<User> allAdminUserInDB = userRepository.findByRole("ADMIN");
        List<String> alladminUser = new ArrayList<>();

        for (User user : allAdminUserInDB) {
            alladminUser.add(user.getEmail());
        }


        return alladminUser;

    }


    public ResponseEntity<?> changePassword(ChangePassword request) {

        try {
            User user = userRepository.findByEmail(jwtFilter.getCurrentUser());

            if (!user.equals(null)) {


                System.out.print(request.getOldPassword());


                if (passwordEncoder.getPasswordEncoder().matches(request.getOldPassword(), user.getPassword())) {

                    user.setPassword(passwordEncoder.getPasswordEncoder().encode(request.getNewPassword()));
                    userRepository.save(user);


                    return CafeUtils.getResponseEntity("password Successfully updated", HttpStatus.OK);


                } else {

                    return CafeUtils.getResponseEntity("OldPassword is incorrect", HttpStatus.BAD_REQUEST);
                }


            } else {
                return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }


    public ResponseEntity<String> generateOtp(ForgotPassword forgotPassword) throws MessagingException {

        try {


            User user = userRepository.findByEmail(forgotPassword.getEmail());
            if (!Objects.isNull(user) && Strings.isNotEmpty(user.getEmail())) {


                String otp = otpUtil.generateOtp(forgotPassword.getEmail());

                //error in this section
                emailUtil.sendOtpToEmail(user.getEmail(), otp);
                System.out.print("Inside Serive generate otp");

                return CafeUtils.getResponseEntity("OTP  sent to  email  " + user.getEmail() + "   please check your email", HttpStatus.OK);

            } else {


                return CafeUtils.getResponseEntity(user.getEmail() + " email is not Registered", HttpStatus.BAD_REQUEST);

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }


    public ResponseEntity<?> verifyOtp(OtpVerifyWrapper otp) {


        try {

            if (otp.getOtp().equals(null)) {
                return CafeUtils.getResponseEntity("please enter OTP", HttpStatus.BAD_REQUEST);
            } else {

                User user = userRepository.findByEmail(otp.getEmail());
                if (!Objects.isNull(user)) {
                    OtpVerifyResponse otpVerifyResponse = new OtpVerifyResponse();
                    if (otpUtil.verifyOtp(otp.getOtp(), otp.getEmail())) {

                        otpVerifyResponse.setMessage("OTP Is Verified");
                        otpVerifyResponse.setStatus("true");
                        return new ResponseEntity<>(otpVerifyResponse, HttpStatus.OK);

                    } else {

                        otpVerifyResponse.setMessage("OTP Is not Verified");
                        otpVerifyResponse.setStatus("false");
                        return new ResponseEntity<>(otpVerifyResponse, HttpStatus.BAD_REQUEST);
                    }


                } else {
                    return CafeUtils.getResponseEntity("Enter Valid Email", HttpStatus.BAD_REQUEST);
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<String> setPasswordAfterOptIsVerrified(Map<String, String> request) {


        try {


            if (request.get("email") == null) {

                return CafeUtils.getResponseEntity("Please enter Gmail", HttpStatus.BAD_REQUEST);
            } else if (request.get("newPassword") == null) {
                return CafeUtils.getResponseEntity("Please enter new Password Field", HttpStatus.BAD_REQUEST);
            }

            User user = userRepository.findByEmail(request.get("email"));
            if (!Objects.isNull(user)) {

                user.setPassword(passwordEncoder.getPasswordEncoder().encode(request.get("newPassword")));
                userRepository.save(user);
                return CafeUtils.getResponseEntity("Your password is updated", HttpStatus.OK);


            } else {
                return CafeUtils.getResponseEntity("Please enter valid Email", HttpStatus.BAD_REQUEST);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
