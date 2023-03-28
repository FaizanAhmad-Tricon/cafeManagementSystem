package com.cafe.service;


import com.cafe.constent.CafeConstents;
import com.cafe.dao.CategoryRepository;
import com.cafe.dao.ProductRepository;
import com.cafe.entity.Category;
import com.cafe.entity.Product;
import com.cafe.jwt.JwtFilter;
import com.cafe.mapper.ProductMapper;
import com.cafe.utils.CafeUtils;
import com.cafe.wrapper.ProductRequest;
import com.cafe.wrapper.ProductResponse;
import com.cafe.wrapper.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductMapper productMapper;
    @Autowired
    JwtFilter jwtFilter;


    public ResponseEntity<?> addProduct(ProductRequest productRequest) {

        try {

            if (jwtFilter.isAdmin()) {
                Product product = productRepository.findByProductName(productRequest.getProductName());

                if (Objects.isNull(product)) {

                    Category category = Category.builder().id(productRequest.getCategoryId()).build();

                    Product newProduct = Product.builder().

                            productName(productRequest.getProductName())
                            .price(productRequest.getPrice())
                            .description(productRequest.getDescription())
                            .status("true")
                            .category(category)
                            .build();


                    productRepository.save(newProduct);


                    return CafeUtils.getResponseEntity(productRequest.getProductName() + "  is successfully Added", HttpStatus.OK);

                } else
                    return CafeUtils.getResponseEntity(productRequest.getProductName() + "  is already present", HttpStatus.BAD_REQUEST);


            } else {
                return CafeUtils.getResponseEntity(CafeConstents.UN_AUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> getAllProduct() {

        try {


            List<Product> productList = productRepository.findAll();
            List<ProductResponse> productResponseList = new ArrayList<>();
            for (Product product : productList) {
                productResponseList.add(productMapper.toProductResponse(product));

            }

            return new ResponseEntity<>(productResponseList, HttpStatus.OK);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<?> updateProduct(ProductUpdateRequest productUpdateRequest) {

        try {
            if (jwtFilter.isAdmin()) {

                Optional<Product> product = productRepository.findById(productUpdateRequest.getId());

                if (product.isPresent()) {

                    productRepository.save(productMapper.toProductFromProductUpdateRequest(productUpdateRequest));

                    return CafeUtils.getResponseEntity(productUpdateRequest.getProductName() + "  is  Updated", HttpStatus.OK);

                } else {

                    return CafeUtils.getResponseEntity("Product is Found", HttpStatus.BAD_REQUEST);
                }


            } else {

                return CafeUtils.getResponseEntity(CafeConstents.UN_AUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
