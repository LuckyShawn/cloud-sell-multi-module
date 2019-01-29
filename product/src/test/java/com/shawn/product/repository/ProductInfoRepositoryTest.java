package com.shawn.product.repository;

import com.netflix.discovery.converters.Auto;
import com.shawn.product.entities.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
/**
 * @Description 单元测试
 * @Author shawn
 * @create 2019/1/29 0029
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatusTest(){
        List<ProductInfo> list = productInfoRepository.findByProductStatus(1);
        Assert.assertTrue(list.size()>0);
    }
}