package com.plateform.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plateform.api.entity.Storage;
import com.plateform.mapper.StorageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName StorageService
 * @Description: TODO
 * @Author liupantao
 * @Date 2021/7/7
 * @Version V1.0
 **/
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */

    @Override
    public void decrease(Long productId, Integer count) {
        logger.info("------->扣减库存开始");


        Storage storageDb = this.getOne (new LambdaQueryWrapper<Storage> ().eq (Storage::getProductId, productId));
        int residue = storageDb.getTotal () - count;
        boolean update = this.update (new LambdaUpdateWrapper<Storage> ().eq (Storage::getProductId, productId).set (Storage::getTotal, residue));
        logger.info("------->扣减库存结束");
    }

}
