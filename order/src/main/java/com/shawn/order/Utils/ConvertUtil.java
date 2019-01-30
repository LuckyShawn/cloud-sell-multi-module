package com.shawn.order.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shawn.order.dto.OrderDTO;
import com.shawn.order.entities.OrderDetail;
import com.shawn.order.enums.ResultEnum;
import com.shawn.order.exception.OrderException;
import com.shawn.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 对象转换工具类
 * @Author shawn
 * @create 2019/1/30 0030
 */
@Slf4j
public class ConvertUtil {

    public static OrderDTO orderFrom2OrderDTO(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());

        }catch (Exception e){
            log.error("【json转换】异常，string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.CONVERT_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
