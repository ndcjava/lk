package com.zhongjian.dto.user.result;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: ldd
 */
@Data
public class UserResultDTO implements Serializable{

    private static final long serialVersionUID = -1728911295677881024L;

    /**
     *
     */
    private Integer id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 0正常用户1新用户送红包2老用户送红包
     */
    private Integer userType;

    /**
     *  昵称
     */
    private String nick;

    /**
     * 用户积分
     */
    private Integer integral;

    /**
     * 用户头像
     */
    private String pic;

    /**
     * 创建时间
     */
    private Integer ctime;

    /**
     * 1正常0禁用
     */
    private Byte status;

    /**
     * 盐
     */
    private String salt;

    /**
     *
     */
    private Integer prizetimes;

    /**
     *
     */
    private Integer lateMarketid;

    /**
     * 被推广人父级id
     */
    private Integer parentId;

    /**
     * 微信unionid
     */
    private String unionid;

    /**
     * 0外部1内部
     */
    private Integer isInside;

    /**
     * 分享人id
     */
    private Integer shareId;

    /**
     *
     */
    private String loginToken;

    /**
     * VIP激活状态
     */
    private Integer vipStatus;

    /**
     * VIP等级
     */
    private Integer vipLevel;

    /**
     * VIP第一次激活时间
     */
    private Integer vipStart;

    /**
     * VIP到期时间
     */
    private Integer vipExpire;

    /**
     * VIP上一次停止时间
     */
    private Integer vipStop;

    /**
     * 累计VIP推广奖励
     */
    private BigDecimal vipPromotionReward;

    /**
     * 已提现VIP推广奖励
     */
    private BigDecimal vipPromotionRewardget;

    /**
     * 未提现VIP推广奖励
     */
    private BigDecimal vipPromotionRewardover;

    /**
     * 微信小程序openid
     */
    private String appletsOpenid;
}
