package com.shawn.order.enums;

import lombok.Getter;

/**
 * @Description 支付状态枚举
 * @Author shawn
 * @create 2019/1/30 0030
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
