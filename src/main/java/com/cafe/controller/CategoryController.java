package com.cafe.controller;


import com.cafe.constent.CafeConstents;
import com.cafe.wrapper.CategoryRequest;
import com.cafe.service.CategoryService;
import com.cafe.utils.CafeUtils;
import com.cafe.wrapper.UpdateCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {


    @Autowired
    CategoryService categoryService;


    @PostMapping(path = "/addCategory")

    public ResponseEntity<?> addCategory(@RequestBody @Valid CategoryRequest request) {

        try {

            return categoryService.addCategory(request);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @PutMapping(path = "/updateCategory")


    public ResponseEntity<String> updateCategory(@RequestBody UpdateCategory updateCategory) {
        try {

            return categoryService.updateCategory(updateCategory);

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }



    @GetMapping(path = "/getAllCategory")
    public ResponseEntity<?> getAllCategory() {

        try {
            return  categoryService.getAllCategory();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }







}
