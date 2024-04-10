package com.chiubilly.springbootmall.service;

import com.chiubilly.springbootmall.dao.Product;
import com.chiubilly.springbootmall.dto.ProductRequest;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);

        void updateProduct(Integer productId,  ProductRequest productRequest);

}

