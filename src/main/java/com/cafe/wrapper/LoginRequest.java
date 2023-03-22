package com.cafe.wrapper;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {


    @NotEmpty(message = "email is required")
    @NotNull
    @Email(message = "enter valid email")
    private String email;


    @NotNull
    @NotEmpty(message = "password is required")
    private  String password;
}
