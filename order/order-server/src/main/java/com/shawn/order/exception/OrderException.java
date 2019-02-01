package com.shawn.order.exception;

import com.shawn.order.enums.ResultEnum;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/30 0030
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
