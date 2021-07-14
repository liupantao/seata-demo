package com.plateform.api.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author IT云清
 */
@FeignClient(value = "SEATA-ORDER")
public interface OrderApi {

    /**
     * 修改订单金额
     * @param userId
     * @param money
     * @param status
     * @return
     */
    @RequestMapping(value = "/order/updateStatus", method = RequestMethod.GET)
    String updateStatus(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("status") Integer status);




    @RequestMapping(value = "/order/updateOrder",method = RequestMethod.GET)
    String updateOrder(@RequestParam("userId") Long userId);
}
