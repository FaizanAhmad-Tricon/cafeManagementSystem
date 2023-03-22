package com.cafe.controller;


import com.cafe.constent.CafeConstents;
import com.cafe.jwt.JwtUtil;
import com.cafe.mapper.ChangePassword;
import com.cafe.mapper.ForgotPassword;
import com.cafe.service.UserService;
import com.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@RestController

@RequestMapping(path = "/user")

public class UserController {


    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @PostMapping(path = "/signup")
    public ResponseEntity<String>  signUp(@RequestBody(required = true)Map<String,String>  requestMap)
    {
        try
        {  return  userService.signUp(requestMap);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }





    //for testing purpose
    @GetMapping(path = "/get")
    public String get(Principal principal)
    {



       return  "Getting respond"+principal.getName();
    }



    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody(required = true) Map<String,String> requestMap)
    {

        try {

            return  userService.login(requestMap);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return   CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }





    //  GetALLUSERAPI

    @GetMapping(path = "/allUser")
    public ResponseEntity<?>  getAllUsers()
    {
        try
        {


                 return userService.getAllUsers();
        }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        return new ResponseEntity<>("{\"error\":\"" + CafeConstents.SOMETHING_WENT_WRONG +" \"}",HttpStatus.INTERNAL_SERVER_ERROR);
    }



    //updateStatus

    @PutMapping(path = "/updateStatus/status/{status}/id/{id}")
    public ResponseEntity<?>  updateStatus(@PathVariable("status") String status , @PathVariable("id") int id)
    {
        try
        {

            return  userService.updateStatus(status , id);

        }catch (Exception ex )
        {
            ex.printStackTrace();
        }

        return  CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }



    //UpdateStatus End




    //ChangePassword




    @PutMapping(path = "/changePassword")
    public ResponseEntity<?>  changePassword(@RequestBody  @Valid ChangePassword  request)
    {

        try {

            return userService.changePassword(request);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return  CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);



    }


    //changePassword end



    //forgot password


    public   ResponseEntity<?>  forgotPassword(@RequestBody  @Valid ForgotPassword forgotPassword)
    {
        try {
                 userService.forgotPassword(forgotPassword);
        }
        catch (Exception ex)
        {

            ex.printStackTrace();

        }

        return  CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);



    }



}
