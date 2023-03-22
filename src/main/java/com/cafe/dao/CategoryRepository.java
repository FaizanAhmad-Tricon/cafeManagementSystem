package com.cafe.dao;

import com.cafe.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {


    Category findByCategoryName(String categoryName);
}
