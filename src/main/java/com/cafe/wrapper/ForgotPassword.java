package com.cafe.wrapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data@AllArgsConstructor
@NoArgsConstructor
public class ForgotPassword {


    @NotEmpty(message = "email is required to enter")
    @NotNull

    private  String email;
}
