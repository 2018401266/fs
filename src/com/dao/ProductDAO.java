package com.dao;
import com.bean.Product;
import com.bean.Product;
import com.utils.DBUtils;

import java.util.List;

public class ProductDAO {
    //保存产品信息
    public boolean saveProduct(Product product){
        String sql = "insert into product(productId,productName,productYield)"+
                "VALUES(?,?,?);";
        return DBUtils.save(sql,product.getProductId(),product.getProductName(),product.getProductYield());
    }

    //分页查询
    public List<Product> getProductListByPage(String sql) {
        List<Product> productList = DBUtils.getList(Product.class,sql);
        return productList;
    }
    //查询总行数
    public Integer getProductCount() {
        String sql = "select count(*) from product";
        Integer count = DBUtils.getCount(sql);
        return count;
    }
}
