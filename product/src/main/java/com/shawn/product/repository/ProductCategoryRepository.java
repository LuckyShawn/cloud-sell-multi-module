package com.shawn.product.repository;

import com.shawn.product.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/29 0029
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,String> {

    /**
     * 根据种类查询商品
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
