package com.chiubilly.springbootmall.service;

import com.chiubilly.springbootmall.dao.Product;
import com.chiubilly.springbootmall.dto.ProductRequest;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);

        void updateProduct(Integer productId,  ProductRequest productRequest);

        void deleteProductById(Integer productId);
}

