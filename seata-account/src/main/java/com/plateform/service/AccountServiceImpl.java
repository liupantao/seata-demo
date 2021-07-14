package com.plateform.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plateform.api.entity.Account;
import com.plateform.api.feign.OrderApi;
import com.plateform.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author IT云清
 */
@Service("accountServiceImpl")
public class AccountServiceImpl extends ServiceImpl<AccountMapper,Account> implements AccountService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Autowired
    private OrderApi orderApi;

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->扣减账户开始account中");
        //模拟超时异常，全局事务回滚
//        try {
//            Thread.sleep(30*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //模拟异常

        //int i=1/0;

        Account accountDb = this.getOne (new LambdaQueryWrapper<Account> ().eq (Account::getUserId, userId));
        BigDecimal subtract = accountDb.getTotal ().subtract (money);
        boolean update = this.update (new LambdaUpdateWrapper<Account> ().eq (Account::getUserId, userId).set (Account::getTotal, subtract));
        LOGGER.info("------->扣减账户结束account中");

        //修改订单状态，此调用会导致调用成环
        LOGGER.info("修改订单状态开始");
        String mes = orderApi.updateStatus(userId, money.multiply(new BigDecimal("0.09")),0);
        LOGGER.info("修改订单状态结束：{}",mes);
    }
}
