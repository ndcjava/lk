package com.zhongjian.dao.entity.cart.user;

import java.math.BigDecimal;

public class UserBean {
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

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 手机号
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 0正常用户1新用户送红包2老用户送红包
     * @return user_type 0正常用户1新用户送红包2老用户送红包
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 0正常用户1新用户送红包2老用户送红包
     * @param userType 0正常用户1新用户送红包2老用户送红包
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     *  昵称
     * @return nick  昵称
     */
    public String getNick() {
        return nick;
    }

    /**
     *  昵称
     * @param nick  昵称
     */
    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    /**
     * 用户积分
     * @return integral 用户积分
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * 用户积分
     * @param integral 用户积分
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 用户头像
     * @return pic 用户头像
     */
    public String getPic() {
        return pic;
    }

    /**
     * 用户头像
     * @param pic 用户头像
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * 创建时间
     * @return ctime 创建时间
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     * 创建时间
     * @param ctime 创建时间
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    /**
     * 1正常0禁用
     * @return status 1正常0禁用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 1正常0禁用
     * @param status 1正常0禁用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 盐
     * @return salt 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 盐
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 
     * @return prizetimes 
     */
    public Integer getPrizetimes() {
        return prizetimes;
    }

    /**
     * 
     * @param prizetimes 
     */
    public void setPrizetimes(Integer prizetimes) {
        this.prizetimes = prizetimes;
    }

    /**
     * 
     * @return late_marketid 
     */
    public Integer getLateMarketid() {
        return lateMarketid;
    }

    /**
     * 
     * @param lateMarketid 
     */
    public void setLateMarketid(Integer lateMarketid) {
        this.lateMarketid = lateMarketid;
    }

    /**
     * 被推广人父级id
     * @return parent_id 被推广人父级id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 被推广人父级id
     * @param parentId 被推广人父级id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 微信unionid
     * @return unionid 微信unionid
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 微信unionid
     * @param unionid 微信unionid
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    /**
     * 0外部1内部
     * @return is_inside 0外部1内部
     */
    public Integer getIsInside() {
        return isInside;
    }

    /**
     * 0外部1内部
     * @param isInside 0外部1内部
     */
    public void setIsInside(Integer isInside) {
        this.isInside = isInside;
    }

    /**
     * 分享人id
     * @return share_id 分享人id
     */
    public Integer getShareId() {
        return shareId;
    }

    /**
     * 分享人id
     * @param shareId 分享人id
     */
    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    /**
     * 
     * @return login_token 
     */
    public String getLoginToken() {
        return loginToken;
    }

    /**
     * 
     * @param loginToken 
     */
    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken == null ? null : loginToken.trim();
    }

    /**
     * VIP激活状态
     * @return vip_status VIP激活状态
     */
    public Integer getVipStatus() {
        return vipStatus;
    }

    /**
     * VIP激活状态
     * @param vipStatus VIP激活状态
     */
    public void setVipStatus(Integer vipStatus) {
        this.vipStatus = vipStatus;
    }

    /**
     * VIP等级
     * @return vip_level VIP等级
     */
    public Integer getVipLevel() {
        return vipLevel;
    }

    /**
     * VIP等级
     * @param vipLevel VIP等级
     */
    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    /**
     * VIP第一次激活时间
     * @return vip_start VIP第一次激活时间
     */
    public Integer getVipStart() {
        return vipStart;
    }

    /**
     * VIP第一次激活时间
     * @param vipStart VIP第一次激活时间
     */
    public void setVipStart(Integer vipStart) {
        this.vipStart = vipStart;
    }

    /**
     * VIP到期时间
     * @return vip_expire VIP到期时间
     */
    public Integer getVipExpire() {
        return vipExpire;
    }

    /**
     * VIP到期时间
     * @param vipExpire VIP到期时间
     */
    public void setVipExpire(Integer vipExpire) {
        this.vipExpire = vipExpire;
    }

    /**
     * VIP上一次停止时间
     * @return vip_stop VIP上一次停止时间
     */
    public Integer getVipStop() {
        return vipStop;
    }

    /**
     * VIP上一次停止时间
     * @param vipStop VIP上一次停止时间
     */
    public void setVipStop(Integer vipStop) {
        this.vipStop = vipStop;
    }

    /**
     * 累计VIP推广奖励
     * @return vip_promotion_reward 累计VIP推广奖励
     */
    public BigDecimal getVipPromotionReward() {
        return vipPromotionReward;
    }

    /**
     * 累计VIP推广奖励
     * @param vipPromotionReward 累计VIP推广奖励
     */
    public void setVipPromotionReward(BigDecimal vipPromotionReward) {
        this.vipPromotionReward = vipPromotionReward;
    }

    /**
     * 已提现VIP推广奖励
     * @return vip_promotion_rewardget 已提现VIP推广奖励
     */
    public BigDecimal getVipPromotionRewardget() {
        return vipPromotionRewardget;
    }

    /**
     * 已提现VIP推广奖励
     * @param vipPromotionRewardget 已提现VIP推广奖励
     */
    public void setVipPromotionRewardget(BigDecimal vipPromotionRewardget) {
        this.vipPromotionRewardget = vipPromotionRewardget;
    }

    /**
     * 未提现VIP推广奖励
     * @return vip_promotion_rewardover 未提现VIP推广奖励
     */
    public BigDecimal getVipPromotionRewardover() {
        return vipPromotionRewardover;
    }

    /**
     * 未提现VIP推广奖励
     * @param vipPromotionRewardover 未提现VIP推广奖励
     */
    public void setVipPromotionRewardover(BigDecimal vipPromotionRewardover) {
        this.vipPromotionRewardover = vipPromotionRewardover;
    }

    /**
     * 微信小程序openid
     * @return applets_openid 微信小程序openid
     */
    public String getAppletsOpenid() {
        return appletsOpenid;
    }

    /**
     * 微信小程序openid
     * @param appletsOpenid 微信小程序openid
     */
    public void setAppletsOpenid(String appletsOpenid) {
        this.appletsOpenid = appletsOpenid == null ? null : appletsOpenid.trim();
    }
}