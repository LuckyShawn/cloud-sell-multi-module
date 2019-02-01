package com.shawn.order.Utils;

import com.shawn.order.VO.ResultVO;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/30 0030
 */
public class ResultVOUtil {

        /**
         * 接口成功返回数据
         * @param obj
         * @return
         */
        public static ResultVO success(Object obj){
            ResultVO resultVO = new ResultVO<>();
            resultVO.setData(obj);
            resultVO.setCode(0);
            resultVO.setMsg("success");
            return resultVO;
        }
}
