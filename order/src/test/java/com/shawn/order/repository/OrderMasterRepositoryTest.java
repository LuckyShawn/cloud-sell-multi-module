package com.shawn.order.repository;

import com.shawn.order.OrderApplicationTests;
import com.shawn.order.entities.OrderMaster;
import com.shawn.order.enums.OrderStatusEnum;
import com.shawn.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/30 0030
 */
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests{
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("shawn");
        orderMaster.setBuyerPhone("18888888888");
        orderMaster.setBuyerAddress("深圳市");
        orderMaster.setBuyerOpenid("100000");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }


}