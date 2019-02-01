package com.shawn.product.service.impl;

import com.shawn.product.dto.CartDTO;
import com.shawn.product.ProductApplicationTests;
import com.shawn.product.entities.ProductInfo;
import com.shawn.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/29 0029
 */
@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void testDecreasedStocked() throws Exception{
        CartDTO cartDTO = new CartDTO("157875227953464068",5);
        productService.decreaseStock(Arrays.asList(cartDTO));

    }
}
