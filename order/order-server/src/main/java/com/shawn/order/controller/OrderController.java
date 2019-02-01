package com.shawn.order.controller;

import com.shawn.order.Utils.ConvertUtil;
import com.shawn.order.Utils.ResultVOUtil;
import com.shawn.order.VO.ResultVO;
import com.shawn.order.dto.OrderDTO;
import com.shawn.order.enums.ResultEnum;
import com.shawn.order.exception.OrderException;
import com.shawn.order.form.OrderForm;
import com.shawn.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/30 0030
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 参数校验，创建订单
     * @param orderForm
     * @param bindingResult
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            //getDefaultMessage获取的是 @NotEmpty(message = "姓名必填") 中的message
            throw new OrderException(1,bindingResult.getFieldError().getDefaultMessage());
        }
        //orderForm -> orderDTO
        OrderDTO orderDTO = ConvertUtil.orderFrom2OrderDTO(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
             log.error("【创建订单】购物车信息为空");
             throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }
}
