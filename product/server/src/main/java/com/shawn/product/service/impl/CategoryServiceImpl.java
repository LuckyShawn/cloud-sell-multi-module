package com.shawn.product.service.impl;

import com.shawn.product.entities.ProductCategory;
import com.shawn.product.repository.ProductCategoryRepository;
import com.shawn.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/29 0029
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {

        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
