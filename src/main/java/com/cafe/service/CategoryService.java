package com.cafe.service;


import com.cafe.constent.CafeConstents;
import com.cafe.dao.CategoryRepository;
import com.cafe.entity.Category;
import com.cafe.jwt.JwtFilter;
import com.cafe.mapper.CategoryMapperImp;
import com.cafe.wrapper.CategoryRequest;
import com.cafe.utils.CafeUtils;
import com.cafe.wrapper.UpdateCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class CategoryService {


    @Autowired
    CategoryMapperImp categoryMapper;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    JwtFilter jwtFilter;


    public ResponseEntity<?> addCategory(CategoryRequest request) {
        try {

            if (jwtFilter.isAdmin()) {


                Category category = categoryRepository.findByCategoryName(request.getCategoryName());

                if (Objects.isNull(category)) {


                    categoryRepository.save(categoryMapper.tocategory(request));


                    return CafeUtils.getResponseEntity(request.getCategoryName() + "  category is added Successfully", HttpStatus.OK);


                } else {

                    return CafeUtils.getResponseEntity(request.getCategoryName() + "   category is already present", HttpStatus.BAD_REQUEST);
                }


            } else {
                return CafeUtils.getResponseEntity(CafeConstents.UN_AUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<String> updateCategory(UpdateCategory updateCategory) {


        try {

            if (jwtFilter.isAdmin()) {

                Optional<Category> category = categoryRepository.findById(updateCategory.getId());

                if (category.isPresent()) {
                    category.get().setCategoryName(updateCategory.getCategoryName());
                    categoryRepository.save(category.get());


                    return ResponseEntity.ok("Category is updated");

                } else {
                    return CafeUtils.getResponseEntity("Category  not Found", HttpStatus.BAD_REQUEST);
                }

            } else {

                return CafeUtils.getResponseEntity(CafeConstents.UN_AUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }

    public ResponseEntity<?> getAllCategory() {


        if(jwtFilter.isAdmin())
        {
            return   ResponseEntity.ok(categoryRepository.findAll());
        }else
        {
             return   new ResponseEntity<>(categoryRepository.getAllCategory(),HttpStatus.OK);
        }



    }

}
