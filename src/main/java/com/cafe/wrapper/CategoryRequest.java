package com.cafe.wrapper;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryRequest {



    @NotEmpty(message = "categoryName is required")
    @NotNull(message = "categoryName field must not be empty")
    private  String categoryName;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse {

      private  Integer id;
        private String name;




        private String email;




        private  String contactNumber;







        private String role;




        private String status;


    }
}
