package com.shawn.order.service;

import com.shawn.order.dto.OrderDTO;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/30 0030
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
