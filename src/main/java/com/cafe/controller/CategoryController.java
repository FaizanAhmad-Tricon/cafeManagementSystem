package com.cafe.controller;


import com.cafe.constent.CafeConstents;
import com.cafe.dao.CategoryRepository;
import com.cafe.entity.Category;
import com.cafe.mapper.CategoryRequest;
import com.cafe.service.CategoryService;
import com.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {


   @Autowired
    CategoryService categoryService;




    public ResponseEntity<?>  addCategory(@RequestBody CategoryRequest request)
    {

        try
        {

            return  categoryService.addCategory(request);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);




    }





}
