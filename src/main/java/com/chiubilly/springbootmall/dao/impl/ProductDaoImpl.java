package com.chiubilly.springbootmall.dao.impl;

import com.chiubilly.springbootmall.model.Product;
import com.chiubilly.springbootmall.dao.ProductDao;
import com.chiubilly.springbootmall.dto.ProductQueryParams;
import com.chiubilly.springbootmall.dto.ProductRequest;
import com.chiubilly.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql = "SELECT count(*) from PRODUCT WHERE 1=1 ";

        Map<String,Object> map = new HashMap<>();
        //查詢條件
//        if(productQueryParams.getCategory()!=null) {
//            sql += " and category=:category";
//            map.put("category",productQueryParams.getCategory().name());
//        }
//
//        if(productQueryParams.getSearch()!=null) {
//            sql += " and product_name like :search ";
//            map.put("search", "%"+productQueryParams.getSearch()+"%");
//        }

        //取代上述寫法
        sql = addFilteringSql(sql,map,productQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);

        return total;
    }

    public List<Product> getProducts(ProductQueryParams productQueryParams){

        String sql = " select product_id, " +
                "product_name, " +
                "category, " +
                "image_url, " +
                "price, " +
                "stock, " +
                "description, " +
                "created_date, " +
                "last_modified_date " +
                "from product WHERE 1=1 ";

        Map<String,Object> map = new HashMap<>();


        //查詢條件
//        if(productQueryParams.getCategory()!=null) {
//            sql += " and category=:category";
//            map.put("category",productQueryParams.getCategory().name());
//        }
//
//        if(productQueryParams.getSearch()!=null) {
//            sql += " and product_name like :search ";
//            map.put("search", "%"+productQueryParams.getSearch()+"%");
//        }

        //取代上述寫法
        sql = addFilteringSql(sql,map,productQueryParams);

        //排序
        //spring 的限制只能用字串拼接用orderBY 無法使用 :變數
        //order by & sort 都有預設值
        sql += " ORDER BY "+ productQueryParams.getOrderBy() + " " +productQueryParams.getSort() ;


        //分頁限制 MySQL限定
        sql += "  LIMIT :limit  OFFSET :offset  ";
        map.put("limit" , productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());



        System.out.println("sql=["+sql+"]");



        List<Product> productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());

        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = " select product_id, " +
                "product_name, " +
                "category, " +
                "image_url, " +
                "price, " +
                "stock, " +
                "description, " +
                "created_date, " +
                "last_modified_date " +
                "from product where product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if(productList.size()>0)
            return productList.get(0);
        else
            return null;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product (product_name, category ,Image_Url, price, stock, description, created_date, last_modified_date)  " +
                "values(:productName, :category, :imageUrl , :price, :stock, :description, :createdDate, :lastModifiedDate) ";
        Map<String,Object> map = new HashMap<>();
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate" ,now);
        map.put("lastModifiedDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);
        int productId = keyHolder.getKey().intValue();


        return productId;
    }

    //----------------------------

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {

        String sql = "UPDATE product SET product_name = :productName ," +
                "category = :category ,image_url=:imageUrl, price = :price, " +
                "stock = :stock , description = :description , " +
                "last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId";

        Map<String , Object> map = new HashMap<>();
        map.put("productId",productId);

        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        map.put("lastModifiedDate" , new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }
    //--------------------------


    @Override
    public void updateStock(Integer productId, Integer stock) {
        String sql = " UPDATE product SET stock = :stock , last_modified_date = :lastModifiedDate  " +
                " WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("stock",stock);
        map.put("lastModifiedDate",new Date());
         namedParameterJdbcTemplate.update(sql,map);



    }

    //------------------
    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    //***********
    private String addFilteringSql(String sql , Map map , ProductQueryParams productQueryParams){
        if(productQueryParams.getCategory()!=null) {
            sql += " and category=:category";
            map.put("category",productQueryParams.getCategory().name());
        }

        if(productQueryParams.getSearch()!=null) {
            sql += " and product_name like :search ";
            map.put("search", "%"+productQueryParams.getSearch()+"%");
        }

        return sql;
    }









    //*********
    //---------------------------
}
