package com.cafe.mapper;

import com.cafe.entity.Product;
import com.cafe.wrapper.ProductResponse;
import com.cafe.wrapper.ProductUpdateRequest;

public interface ProductMapper {

    ProductResponse toProductResponse(Product product);


    Product  toProductFromProductUpdateRequest(ProductUpdateRequest productUpdateRequest);
}
