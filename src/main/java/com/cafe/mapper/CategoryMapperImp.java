package com.cafe.mapper;

import com.cafe.entity.Category;
import com.cafe.wrapper.CategoryRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapperImp implements  CategoryMapper{

    @Override
    public Category tocategory(CategoryRequest request) {
         Category  category = new Category();

         category.setCategoryName(request.getCategoryName());
         return  category;
    }
}
