package com.shawn.product.exception;

import com.shawn.product.enums.ResultEnum;

/**
 * @Description 商品异常类
 * @Author shawn
 * @create 2019/1/31 0031
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
