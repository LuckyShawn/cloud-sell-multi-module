package com.shawn.product.service;

import com.shawn.product.entities.ProductInfo;

import java.util.List;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/29 0029
 */
public interface ProductService {
    /**
     * 查询所有上架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有订单中的商品
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

}
