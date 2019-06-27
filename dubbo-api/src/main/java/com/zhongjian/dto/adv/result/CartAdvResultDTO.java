package com.zhongjian.dto.adv.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ldd
 */
@Data
public class CartAdvResultDTO implements Serializable {

    private static final long serialVersionUID = -5948092855255306532L;

    /**
     * 类型
     */
    private Integer advType;

    /**
     * 标题
     */
    private String title;

    /**
     * 照片
     */
    private String pic;

    /**
     * 地址
     */
    private String url;
    /**
     * 商品id
     */
    private Integer gid;
}
