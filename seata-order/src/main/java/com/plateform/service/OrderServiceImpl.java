package com.plateform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plateform.api.entity.Order;
import com.plateform.api.feign.AccountApi;
import com.plateform.api.feign.StorageApi;
import com.plateform.mapper.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @ClassName OrderService
 * @Description: TODO
 * @Author liupantao
 * @Date 2021/7/7
 * @Version V1.0
 **/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements  OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);



    @Autowired
    private StorageApi storageApi;
    @Autowired
    private AccountApi accountApi;


    @Autowired
    private OrderMapper orderMapper;

    @GlobalTransactional(timeoutMills = 300000, name = "OrderService-addOrder")
    @Override
    public  String addOrder(Order order){
        logger.info ("----->交易开始");
        //本地方法 创建订单
        boolean save = this.save (order);
        //调用远程库存服务
        storageApi.decrease (order.getProductId (),order.getCount ());
        //调用远程账号服务
        accountApi.decrease (order.getUserId (),order.getMoney ());
        logger.info ("----->交易结束");
        return  "";
    }

    @Override
    public void updateOrderStatus(Long userId, BigDecimal money, Integer status) {
        this.update ( new LambdaUpdateWrapper<Order> ().eq (Order::getUserId,userId)
                .set (Order::getMoney,money).set (Order::getStatus,status));
    }

}
