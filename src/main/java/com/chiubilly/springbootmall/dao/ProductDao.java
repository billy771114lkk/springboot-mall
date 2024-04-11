package com.chiubilly.springbootmall.dao;

import com.chiubilly.springbootmall.constant.ProductCategory;
import com.chiubilly.springbootmall.dto.ProductRequest;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category,String search);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
