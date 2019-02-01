package com.shawn.order.enums;

import lombok.Getter;

/**
 * @Description 返回信息状态枚举
 * @Author shawn
 * @create 2019/1/30 0030
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(2001,"参数错误"),
    CONVERT_ERROR(2002, "转换异常"),
    CART_EMPTY(2003, "购物车为空"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
