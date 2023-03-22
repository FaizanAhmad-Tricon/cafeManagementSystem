package com.cafe.service;


import com.cafe.constent.CafeConstents;
import com.cafe.dao.CategoryRepository;
import com.cafe.entity.Category;
import com.cafe.mapper.CategoryMapper;
import com.cafe.mapper.CategoryRequest;
import com.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


//    @Autowired
//    CategoryMapper categoryMapper;


    public ResponseEntity<?> addCategory(CategoryRequest request) {
        try {


            Category category = categoryRepository.findByCategoryName(request.getCategoryName());

            if (Objects.isNull(category)) {



               // categoryRepository.save(categoryMapper.tocategory(request));


                return CafeUtils.getResponseEntity(request.getCategoryName() + "category is added Successfully", HttpStatus.OK);


            } else {

                return CafeUtils.getResponseEntity(request.getCategoryName() + " category is already present", HttpStatus.BAD_REQUEST);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
