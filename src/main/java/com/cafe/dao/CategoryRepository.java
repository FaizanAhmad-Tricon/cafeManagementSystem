package com.cafe.dao;

import com.cafe.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {


    Category findByCategoryName(String categoryName);


     List<Category> getAllCategory();
}
