package com.shawn.order.service.impl;

import com.shawn.order.Utils.KeyUtil;
import com.shawn.order.client.ProductClient;
import com.shawn.order.dto.CartDTO;
import com.shawn.order.dto.OrderDTO;
import com.shawn.order.entities.OrderDetail;
import com.shawn.order.entities.OrderMaster;
import com.shawn.order.entities.ProductInfo;
import com.shawn.order.enums.OrderStatusEnum;
import com.shawn.order.enums.PayStatusEnum;
import com.shawn.order.repository.OrderDetailRepository;
import com.shawn.order.repository.OrderMasterRepository;
import com.shawn.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ProductClient productClient;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.generateUniqueKey();
        //查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().
                stream().map(OrderDetail::getProductId).
                collect(Collectors.toList());   //获取商品id列表
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);


        //计算商品总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getProductId().equals(orderDetail.getProductId())){
                    //单价*数量
                    orderAmount = productInfo.getProductPrice().
                            multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.generateUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }


        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        //订单写入DB
        OrderMaster orderMaster = new OrderMaster();
        //给DTO设置ID后将DTO的属性也就是用户传入的属性赋copy到orderMaster
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
