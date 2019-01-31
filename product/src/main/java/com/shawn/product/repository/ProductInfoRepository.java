package com.shawn.product.repository;

import com.shawn.product.entities.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description 商品信息dao接口
 * @Author shawn
 * @create 2019/1/29 0029
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 据种商品状态查询商品信息
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /**
     * 根据商品id列表查询商品
     * @param productIdList
     * @return
     */
    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
