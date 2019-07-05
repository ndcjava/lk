package com.zhongjian.dto.goods.result;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: ldd
 */
@Data
public class CartGoodsResultDTO implements Serializable{

    private static final long serialVersionUID = 197018972999527001L;

    /**
     * 规格id
     */
    private Integer id;

    /**
     * 商品id关联hm_goods
     */
    private Integer gid;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 规格名
     */
    private String specName;

}
