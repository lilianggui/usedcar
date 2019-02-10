package com.llg.usedcar.service.interfaces;

import com.llg.usedcar.entity.Appointment;
import com.llg.usedcar.entity.Car;
import com.llg.usedcar.entity.CarImage;

import java.util.List;

/**
 * @Auther: Lange
 * @Date: 2019/1/15 09:37
 * @Description:
 */
public interface CarService {
    List<Car> bgCarList(Car car);

    int addCar(Car car);

    int updateCar(Car car);

    int publishCar(Car car);

    int revokeCar(Car car);

    List<CarImage> carImageList(String carIds);

    int saveCarImgInfo(CarImage carImage);

    List<Car> wxListCar(Car car);

    Car wxCarDetail(Car car);

    int wxCarAppoint(Appointment appointment);

    int changeMainImage(Car car);

    int delCarImage(String delCarImageIds);
}
