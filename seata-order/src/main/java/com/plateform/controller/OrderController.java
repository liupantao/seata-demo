package com.plateform.controller;

import com.plateform.api.entity.Order;
import com.plateform.service.OrderService;
import com.plateform.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @ClassName OrderController
 * @Description: TODO
 * @Author liupantao
 * @Date 2021/7/7
 * @Version V1.0
 **/
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/addOrder")
    @ResponseBody
    public String addOrder(Order order ){
        String res = orderService.addOrder (order);

        return  "create order success !";

    }


    /**
     * 修改订单状态
     * @param userId
     * @param money
     * @param status
     * @return
     */
    @RequestMapping("updateStatus")
    @ResponseBody
    public String updateStatus(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("status") Integer status){
        orderService.updateOrderStatus(userId,money,status);
        return "updateStatus decrease success";
    }

    /**
     * 修改订单状态
     * @param userId
     * @param
     * @param
     * @return
     */
    @RequestMapping("updateOrder")
    @ResponseBody
    public String updateOrder(@RequestParam("userId") Long userId){
        orderService.updateOrderStatus(userId,null,null);
        return "updateStatus decrease success";
    }

}
