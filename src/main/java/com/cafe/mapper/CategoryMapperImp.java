package com.cafe.mapper;

import com.cafe.entity.Category;

import java.util.Map;

public class CategoryMapperImp implements  CategoryMapper{

    @Override
    public Category tocategory(CategoryRequest request) {
         Category  category = new Category();

         category.setCategoryName(request.getCategoryName());
         return  category;
    }
}
