package com.shawn.order.service.impl;

import com.netflix.discovery.converters.Auto;
import com.shawn.order.Utils.KeyUtil;
import com.shawn.order.dto.OrderDTO;
import com.shawn.order.entities.OrderMaster;
import com.shawn.order.enums.OrderStatusEnum;
import com.shawn.order.enums.PayStatusEnum;
import com.shawn.order.repository.OrderDetailRepository;
import com.shawn.order.repository.OrderMasterRepository;
import com.shawn.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/30 0030
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //订单写入DB
        OrderMaster orderMaster = new OrderMaster();
        //给DTO设置ID后将DTO的属性也就是用户传入的属性赋copy到orderMaster
        orderDTO.setOrderId(KeyUtil.generateUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(10));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
