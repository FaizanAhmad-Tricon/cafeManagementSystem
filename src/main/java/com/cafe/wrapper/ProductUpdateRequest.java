package com.cafe.wrapper;


import com.cafe.entity.Category;
import lombok.Data;

import javax.persistence.*;

@Data
public class ProductUpdateRequest {




    private  Integer id;



    private String productName;




    private String  description;







    private Integer categoryId;





    private Integer  price;





    private  String status;



}
