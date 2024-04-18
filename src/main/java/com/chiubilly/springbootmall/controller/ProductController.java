package com.chiubilly.springbootmall.controller;

import com.chiubilly.springbootmall.constant.ProductCategory;
import com.chiubilly.springbootmall.model.Product;
import com.chiubilly.springbootmall.dto.ProductQueryParams;
import com.chiubilly.springbootmall.dto.ProductRequest;
import com.chiubilly.springbootmall.service.ProductService;
import com.chiubilly.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    //public ResponseEntity<List<Product>> getProducts(
    public ResponseEntity<Page<Product>> getProducts(
                                                                                //  查詢排序 Filterig
                                                                                                    @RequestParam(required = false) ProductCategory category
                                                                                                    ,@RequestParam(required = false)String search
                                                                               // 排序SORTING
                                                                                                    ,@RequestParam(defaultValue = "created_date") String orderBy
                                                                                                    ,@RequestParam(defaultValue = "desc") String sort
                                                                                //分頁 pagination
                                                                                                    ,@RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit
                                                                                                    ,@RequestParam(defaultValue = "0")  @Min(0)Integer offset
                                                                                                    ){

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

            //取得productList
            List<Product> productList = productService.getProducts(productQueryParams);
            //取得product總數
            Integer total = productService.countProduct(productQueryParams);

            //分頁
            Page<Product> page = new Page<>();
            page.setLimit(limit);
            page.setOffset(offset);
            page.setTotal(total);
            page.setResults(productList);

            //return ResponseEntity.status(HttpStatus.OK).body(productList);
            return ResponseEntity.status(HttpStatus.OK).body(page);


    }




    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        if(product!=null)
            return ResponseEntity.status(HttpStatus.OK).body(product);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/products")
    public ResponseEntity<Product>  createProduct(@RequestBody @Valid ProductRequest productRequest){
       Integer productId =  productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct (@PathVariable Integer productId ,

                                                                                                @RequestBody @Valid ProductRequest productRequest){
        //Check if product exists or not
        Product product = productService.getProductById(productId);
        if(product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //update product data
        productService.updateProduct(productId, productRequest) ;

        Product updateProcuct =  productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updateProcuct);

    }

    @DeleteMapping("/products/{productId}")
    public  ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    //--------------------------
}
