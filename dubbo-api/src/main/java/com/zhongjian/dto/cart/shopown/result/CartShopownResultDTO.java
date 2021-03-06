package com.zhongjian.dto.cart.shopown.result;

import com.zhongjian.dto.cart.basket.result.CartBasketResultDTO;
import com.zhongjian.dto.cart.storeActivity.result.CartStoreActivityResultDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ldd
 */
@Data
public class CartShopownResultDTO implements Serializable {

    private static final long serialVersionUID = 197018972999527001L;

    /**
     * 商户id
     */
    private Integer pid;
    /**
     * 商户图片
     */
    private String picture;

    /**
     *0优惠1不优惠
     */
    private String unFavorable;

    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 商品总价
     */
    private String totalPrice;

    /**
     * 优惠后价格
     */
    private String discountPrice;

    /**
     * 起步价
     */
    private String startingPrice;

    /**
     * 差价
     */
    private String disparity;

    /**
     * 状态 1达到.0未达到
     */
    private Integer state;

    /**
     * 状态
     */
    private String status;


    /**
     * 状态描述
     */
    private String statusMsg;

    /**
     * 活动描述
     */
    private String activityMsg;

    /**
     * 商家下对应的活动信息
     */
    List<CartStoreActivityResultDTO> storeActivity;

    /**
     * 该用户在商家下对应的食品信息
     */
    List<CartBasketResultDTO> basket;

    /**
     * 备注组合
     */
    private List<String> remarkList;

}
