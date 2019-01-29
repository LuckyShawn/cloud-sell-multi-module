package com.shawn.product.VO;

import lombok.Data;

/**
 * @Description HTTP请求返回的最外层对象
 * @Author shawn
 * @create 2019/1/29 0029
 */
@Data
public class ResultVO<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体信息
     */
    private T data;
}
