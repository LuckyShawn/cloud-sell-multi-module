package com.shawn.product.DTO;

import lombok.Data;

/**
 * @Description 购物车DTO
 * @Author shawn
 * @create 2019/1/31 0031
 */
@Data
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;
    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
