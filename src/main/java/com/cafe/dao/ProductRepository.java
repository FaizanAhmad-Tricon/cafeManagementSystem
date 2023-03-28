package com.cafe.dao;

import com.cafe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product ,Integer> {
    Product findByProductName(String productName);
}
