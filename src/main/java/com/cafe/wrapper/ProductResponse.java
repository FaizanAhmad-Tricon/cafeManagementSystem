package com.cafe.wrapper;


import com.cafe.entity.Category;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
public class ProductResponse {

    private  static  final long  serial_UID = 12345L;







    private  Integer id;



    private String productName;




    private String  description;







    private Integer categoryId;





    private Integer  price;





    private  String status;










}
