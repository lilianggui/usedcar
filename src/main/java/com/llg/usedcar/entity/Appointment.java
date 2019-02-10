package com.llg.usedcar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Appointment {
    private Long appointmentId;
    private Long carId;
    private Long userId;
    private Long shopId;

    private String username;
    private Integer sex;
    private String phone;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date appointmentDate;
    private Integer appointmentStatus;

    private Date createTime;
    private Date updateTime;

    private String carName;
    private BigDecimal carPriceMin;
    private BigDecimal carPriceMax;
    private String mainImage;

    @JsonFormat(pattern="MM-dd HH:mm",timezone = "GMT+8")
    private Date appointmentShortDate;

    public Date getAppointmentShortDate() {
        return appointmentShortDate;
    }

    public void setAppointmentShortDate(Date appointmentShortDate) {
        this.appointmentShortDate = appointmentShortDate;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
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

    public Integer getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(Integer appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
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
