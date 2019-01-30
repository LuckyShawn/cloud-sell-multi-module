package com.shawn.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/30 0030
 */
@RestController
@Slf4j
public class ServerController {

    @GetMapping("/msg")
    public String sendMsg(){
        log.info("product sendMsg");
        return "Message from product";
    }
}
