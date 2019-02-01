package com.shawn.order.Utils;

import java.util.Random;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/30 0030
 */
public class KeyUtil {


    /**
     * 生成唯一的主键
     * 利用时间戳 + 随机数 （并不是百分百随机）
     * @return
     */
    public static synchronized String generateUniqueKey(){
        Random random = new Random();
        Integer num = random.nextInt(900000)+100000;

        return System.currentTimeMillis() + String.valueOf(num);
    }
}
