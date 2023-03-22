package com.cafe.entity;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate

@Table(name = "user")
public class User implements Serializable {

    private static final long   serialVersionUID =1l;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId",nullable = false)


    private Integer id ;


    @Column(name = "name")
    private String name;



    @Column(name="email")
    private String email;



    @Column(name = "contactNumber")
    private  String contactNumber;

    @Column(name = "password")
    private String password;



    @Column(name = "role")
    private String role;



    @Column(name = "status")
    private String status;



    @Column(name = "oneTimePassword")
    private String oneTimePassword;


    @Column(name = "otpGenerationTime")
    private Date  otpGenerationTime;



}
