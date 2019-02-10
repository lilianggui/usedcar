package com.llg.usedcar.service.impl;

import com.llg.usedcar.entity.Appointment;
import com.llg.usedcar.entity.Car;
import com.llg.usedcar.entity.CarImage;
import com.llg.usedcar.mapper.dao.CarMapper;
import com.llg.usedcar.service.interfaces.CarService;
import com.llg.usedcar.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Lange
 * @Date: 2019/1/15 09:37
 * @Description:
 */
@Service
public class CarServiceImpl implements CarService {
    @Resource
    private CarMapper carMapper;

    @Override
    public List<Car> bgCarList(Car car) {
        return carMapper.listCar(car);
    }

    @Override
    public int addCar(Car car) {
        return carMapper.addCar(car);
    }

    @Override
    public int updateCar(Car car) {
        return carMapper.updateCar(car);
    }

    @Override
    public int publishCar(Car car) {
        car.setCarStatus(2);//2为已发布
        return carMapper.updateCar(car);
    }

    @Override
    public int revokeCar(Car car) {
        car.setCarStatus(3);//3为撤销发布
        return carMapper.updateCar(car);
    }

    @Override
    public List<CarImage> carImageList(String carIds) {
        return carMapper.carImageList(carIds);
    }

    @Override
    public int saveCarImgInfo(CarImage carImage){
        if(carMapper.getCarMainImage(carImage) == null){
            carImage.setIsMainImg("1");
        }
        return carMapper.saveCarImgInfo(carImage);
    }

    @Override
    public List<Car> wxListCar(Car car) {
        return carMapper.wxListCar(car);
    }

    @Override
    public Car wxCarDetail(Car car) {
        List<CarImage> carImages = carMapper.carImageList("" + car.getCarId());
        Car carDetail = carMapper.wxCarDetail(car);
        carDetail.setCarImages(carImages);
        return carDetail;
    }

    @Override
    public int wxCarAppoint(Appointment appointment) {
        appointment.setAppointmentId(CommonUtils.generatePK());
        Date now = new Date();
        appointment.setCreateTime(now);
        appointment.setUpdateTime(now);
        return carMapper.wxCarAppoint(appointment);
    }

    @Override
    public int changeMainImage(Car car) {
        return carMapper.changeMainImage(car);
    }

    @Override
    public int delCarImage(String delCarImageIds) {
        return carMapper.delCarImage(delCarImageIds);
    }
}
