package com.cafe.wrapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {


    @NotEmpty(message = "name is required")
    @NotNull
    private String name;



    @NotEmpty(message = "email is required")
    @NotNull
    @Email


    private  String email;



    @NotNull
    @NotEmpty(message = "password is required")
    private  String  password;


    @NotNull
    @NotEmpty(message = "contact number is required")
    private  String contactNumber;


}
