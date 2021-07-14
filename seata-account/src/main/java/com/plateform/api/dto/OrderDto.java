package com.plateform.api.dto;

import java.io.Serializable;

/**
 * @ClassName OrderDto
 * @Description: TODO
 * @Author liupantao
 * @Date 2021/7/14
 * @Version V1.0
 **/
public class OrderDto implements Serializable {

    private Long userId ;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
