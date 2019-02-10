package com.llg.usedcar.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Lange
 * @Date: 2019/1/15 09:33
 * @Description:
 */
public class Car {
    private Long carId;
    private Long shopId;
    private String carName;
    private BigDecimal carPriceMin;
    private BigDecimal carPriceMax;
    private String carSlogan;
    private String mainImage;
    private String introduction;
    private Integer carStatus;
    private Integer carIndex;
    private Date createTime;
    private Date updateTime;

    private Long mainImageId;

    //扩展字段（预约）
    private Integer isAppoint;//是否预约（登录用户，详情页）
    private Long appointmentId;//预约id
    private Date appointmentDate;
    private Date maxAppointmentDate;
    private Integer appointmentStatus;
    private Long userId;

    List<CarImage> carImages;

    public Integer getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(Integer appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Date getMaxAppointmentDate() {
        return maxAppointmentDate;
    }

    public void setMaxAppointmentDate(Date maxAppointmentDate) {
        this.maxAppointmentDate = maxAppointmentDate;
    }

    public Long getMainImageId() {
        return mainImageId;
    }

    public void setMainImageId(Long mainImageId) {
        this.mainImageId = mainImageId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIsAppoint() {
        return isAppoint;
    }

    public void setIsAppoint(Integer isAppoint) {
        this.isAppoint = isAppoint;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<CarImage> getCarImages() {
        return carImages;
    }

    public void setCarImages(List<CarImage> carImages) {
        this.carImages = carImages;
    }

    public Integer getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(Integer carIndex) {
        this.carIndex = carIndex;
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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public BigDecimal getCarPriceMin() {
        return carPriceMin;
    }

    public void setCarPriceMin(BigDecimal carPriceMin) {
        this.carPriceMin = carPriceMin;
    }

    public BigDecimal getCarPriceMax() {
        return carPriceMax;
    }

    public void setCarPriceMax(BigDecimal carPriceMax) {
        this.carPriceMax = carPriceMax;
    }

    public String getCarSlogan() {
        return carSlogan;
    }

    public void setCarSlogan(String carSlogan) {
        this.carSlogan = carSlogan;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }
}
