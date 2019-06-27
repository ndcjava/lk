package com.zhongjian.dao.entity.cart.adv;

public class CartAdvBean {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer advType;

    /**
     * 
     */
    private Integer showType;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String pic;

    /**
     * 
     */
    private Integer order;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String gid;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private Integer ctime;

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
     * 
     * @return adv_type 
     */
    public Integer getAdvType() {
        return advType;
    }

    /**
     * 
     * @param advType 
     */
    public void setAdvType(Integer advType) {
        this.advType = advType;
    }

    /**
     * 
     * @return show_type 
     */
    public Integer getShowType() {
        return showType;
    }

    /**
     * 
     * @param showType 
     */
    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    /**
     * 
     * @return title 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 
     * @return pic 
     */
    public String getPic() {
        return pic;
    }

    /**
     * 
     * @param pic 
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * 
     * @return order 
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 
     * @param order 
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 
     * @return url 
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url 
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 
     * @return gid 
     */
    public String getGid() {
        return gid;
    }

    /**
     * 
     * @param gid 
     */
    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    /**
     * 
     * @return status 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return ctime 
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     * 
     * @param ctime 
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }
}