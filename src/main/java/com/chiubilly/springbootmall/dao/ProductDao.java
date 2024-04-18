package com.chiubilly.springbootmall.dao;

import com.chiubilly.springbootmall.dto.ProductQueryParams;
import com.chiubilly.springbootmall.dto.ProductRequest;
import com.chiubilly.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);

    void updateStock(Integer productId,Integer stock);

    void deleteProductById(Integer productId);

}
