package com.cafe.mapper;


import com.cafe.entity.Category;
import com.cafe.wrapper.CategoryRequest;
import org.mapstruct.Mapper;



public interface CategoryMapper {



    Category tocategory(CategoryRequest request);


}
