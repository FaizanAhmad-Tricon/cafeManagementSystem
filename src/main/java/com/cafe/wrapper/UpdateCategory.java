package com.cafe.wrapper;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCategory {


    @NotNull
    @NotEmpty
    private Integer id;
    private String categoryName;


}


