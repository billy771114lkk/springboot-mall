package com.chiubilly.springbootmall.dao;

import com.chiubilly.springbootmall.dto.ProductRequest;

public interface ProductDao {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
