package com.zhongjian.dto.cart.market.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ldd
 */
@Data
public class CartMarketListResultDTO  implements Serializable{

    private static final long serialVersionUID = 197018972999527001L;

    /**
     *
     */
    private Integer id;

    /**
     * 菜场名称
     */
    private String ename;

    /**
     * 添加时间
     */
    private Integer ctime;

    /**
     * 菜场图片
     */
    private String marketPic;

    /**
     * 是否可用1可用0不可用
     */
    private Integer state;

    /**
     * 顺序
     */
    private Integer order;
}
