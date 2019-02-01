package com.shawn.product.service;

import com.shawn.product.entities.ProductCategory;

import java.util.List;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/29 0029
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
