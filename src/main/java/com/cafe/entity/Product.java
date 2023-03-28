package com.cafe.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Builder

@DynamicInsert
@DynamicUpdate

public class Product   implements  Serializable  {


    private  static  final long  serial_UID = 12345L;






    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Integer id;


    @Column(name = "name")
    private String productName;



    @Column(name = "description")
    private String  description;






    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk"  ,nullable = false)
    private  Category category;




    @Column(name = "price")
    private Integer  price;



    @Column(name = "status")

    private  String status;









}
