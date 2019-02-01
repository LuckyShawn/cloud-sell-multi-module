package com.shawn.order.controller;

import com.shawn.order.client.ProductClient;
import com.shawn.order.dto.CartDTO;
import com.shawn.order.entities.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/30 0030
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTO("164103465734242707",5)));
        return "ok";
    }

    @GetMapping("/listForOrder")
    public String getProductList(){
        List<ProductInfo> productInfos = productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("response={}",productInfos);
        return "ok";
    }

    /**
     * 测试RestTemplate
     *
     * @return
     */
    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //1.第一种方式：直接使用restTemplate，url写死，地址不灵活
        //RestTemplate restTemplate = new RestTemplate();
        //String msg = restTemplate.getForObject("http://localhost:8901/msg",String.class);


        //2.第二种方式：利用loadBalancerClient，通过应用名获取url，过于繁杂
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s%s", serviceInstance.getHost(), ":"+ serviceInstance.getPort() + "/msg");
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject(url,String.class);

        //3.第三种方式：利用@LoadBalanced，restTemplate直接使用应用名调用（负载均衡）,此方法最优
        //String msg = restTemplate.getForObject("http://PRODUCT/msg",String.class);
        log.info("msg={}", msg);
        return msg;
    }
}
