package com.shawn.product.service.impl;

import com.netflix.discovery.converters.Auto;
import com.shawn.product.ProductApplicationTests;
import com.shawn.product.entities.ProductCategory;
import com.shawn.product.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/29 0029
 */
@Component
public class CategoryServiceImplTest extends ProductApplicationTests {

    @Autowired
    private CategoryService categoryService;
    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = categoryService.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(list.size()>0);
    }
}