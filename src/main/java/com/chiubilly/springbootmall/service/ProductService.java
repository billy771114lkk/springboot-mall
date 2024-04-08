package com.chiubilly.springbootmall.service;

import com.chiubilly.springbootmall.dao.Product;

public interface ProductService {
    Product getProductById(Integer productId);
}
