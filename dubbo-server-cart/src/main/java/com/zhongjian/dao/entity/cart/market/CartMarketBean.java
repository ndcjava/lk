package com.zhongjian.dao.entity.cart.market;

public class CartMarketBean {
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
     * 菜场名称
     * @return ename 菜场名称
     */
    public String getEname() {
        return ename;
    }

    /**
     * 菜场名称
     * @param ename 菜场名称
     */
    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    /**
     * 添加时间
     * @return ctime 添加时间
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     * 添加时间
     * @param ctime 添加时间
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    /**
     * 菜场图片
     * @return market_pic 菜场图片
     */
    public String getMarketPic() {
        return marketPic;
    }

    /**
     * 菜场图片
     * @param marketPic 菜场图片
     */
    public void setMarketPic(String marketPic) {
        this.marketPic = marketPic == null ? null : marketPic.trim();
    }

    /**
     * 是否可用1可用0不可用
     * @return state 是否可用1可用0不可用
     */
    public Integer getState() {
        return state;
    }

    /**
     * 是否可用1可用0不可用
     * @param state 是否可用1可用0不可用
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 顺序
     * @return order 顺序
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 顺序
     * @param order 顺序
     */
    public void setOrder(Integer order) {
        this.order = order;
    }
}