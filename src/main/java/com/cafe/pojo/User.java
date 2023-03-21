package com.cafe.pojo;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "id")


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



}
