package com.llg.usedcar.entity;

import java.util.Date;

/**
 * @Auther: Lange
 * @Date: 2019/1/15 10:41
 * @Description:
 */
public class CarImage {
    private Long carImageId;
    private Long carId;
    private String url;
    private String isMainImg;
    private Date createTime;
    private Date updateTime;

    public CarImage() { }

    public CarImage(Long carImageId, Long carId, String url, String isMainImg, Date createTime, Date updateTime) {
        this.carImageId = carImageId;
        this.carId = carId;
        this.url = url;
        this.isMainImg = isMainImg;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getCarImageId() {
        return carImageId;
    }

    public void setCarImageId(Long carImageId) {
        this.carImageId = carImageId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsMainImg() {
        return isMainImg;
    }

    public void setIsMainImg(String isMainImg) {
        this.isMainImg = isMainImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
