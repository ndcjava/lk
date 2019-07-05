package com.zhongjian.dao.entity.cart.goods;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartGoodsSpecBean {

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

    /**
     * 规格id
     *
     * @return id 规格id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 规格id
     *
     * @param id 规格id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 商品id关联hm_goods
     *
     * @return gid 商品id关联hm_goods
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * 商品id关联hm_goods
     *
     * @param gid 商品id关联hm_goods
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * 价格
     *
     * @return price 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 价格
     *
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 规格名
     *
     * @return spec_name 规格名
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * 规格名
     *
     * @param specName 规格名
     */
    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }
}