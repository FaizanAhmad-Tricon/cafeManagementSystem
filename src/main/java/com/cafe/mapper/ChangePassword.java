package com.cafe.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword {


    @NotEmpty(message = "OldPassword is required")
    @NotNull(message = "OldPassword must noy be null")
    private String oldPassword;



    @NotNull(message = "NewPassword must noy be null")
    @NotEmpty(message = "NewPassword is required")
    private String newPassword;


}
