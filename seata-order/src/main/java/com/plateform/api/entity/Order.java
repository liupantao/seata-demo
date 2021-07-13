package com.plateform.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName Order
 * @Description: TODO
 * @Author liupantao
 * @Date 2021/7/7
 * @Version V1.0
 **/
@Data
@TableName("sys_order")
public class Order implements Serializable {

    @TableField(value = "id",fill = FieldFill.INSERT)
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("product_id")
    private Long productId;

    @TableField("count")
    private Integer count;

    @TableField("money")
    private BigDecimal money;

    /**订单状态：0：创建中；1：已完结*/
    @TableField("status")
    private Integer status;


}
