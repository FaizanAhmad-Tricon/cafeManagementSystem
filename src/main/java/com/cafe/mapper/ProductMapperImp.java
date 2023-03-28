package com.cafe.mapper;

import com.cafe.entity.Category;
import com.cafe.entity.Product;
import com.cafe.wrapper.ProductResponse;
import com.cafe.wrapper.ProductUpdateRequest;
import org.springframework.stereotype.Service;


@Service
public class ProductMapperImp implements ProductMapper {

    @Override
    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder().
                productName(product.getProductName())
                .id(product.getId())
                .price(product.getPrice())
                .status(product.getStatus())
                .description(product.getDescription())
                .categoryId(product.getCategory().getId()).build();


    }

    @Override
    public Product toProductFromProductUpdateRequest(ProductUpdateRequest productUpdateRequest) {


        Category category = Category.builder().id(productUpdateRequest.getId()).build();

        return Product.builder().id(productUpdateRequest.getId())
                .productName(productUpdateRequest.getProductName())
                .price(productUpdateRequest.getPrice())
                .description(productUpdateRequest.getDescription())
                .status(productUpdateRequest.getStatus())
                .build();

    }
}
