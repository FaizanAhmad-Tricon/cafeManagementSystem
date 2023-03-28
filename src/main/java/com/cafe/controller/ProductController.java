package com.cafe.controller;


import com.cafe.constent.CafeConstents;
import com.cafe.service.ProductService;
import com.cafe.utils.CafeUtils;
import com.cafe.wrapper.ProductRequest;
import com.cafe.wrapper.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping(path = "/addProduct")

    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {

        try {


            return productService.addProduct(productRequest);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @GetMapping(path = "/getAllProduct")
    public ResponseEntity<?>  getAllProduct()
    {
        try {


            return productService.getAllProduct();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @PutMapping(path = "/updateProduct")
    public ResponseEntity<?>  updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest)
    {

        try
        {


            return  productService.updateProduct(productUpdateRequest);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }


        return  CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);


    }

}
