package com.cafe.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
@DynamicUpdate
@DynamicInsert
public class Category implements Serializable {

    private static final long  serial_ID= 1L ;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId",nullable = false)
    private   Integer id;


    @Column(name = "Categoryname")
    private   String  categoryName;


}
