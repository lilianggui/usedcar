package com.llg.usedcar.mapper.dao;

import com.llg.usedcar.entity.Appointment;
import com.llg.usedcar.entity.Car;
import com.llg.usedcar.entity.CarImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: Lange
 * @Date: 2019/1/15 09:38
 * @Description:
 */
public interface CarMapper {

    List<Car> listCar(Car car);

    int addCar(Car car);

    int updateCar(Car car);

    List<CarImage> carImageList(@Param("carIds") String carIds);

    int saveCarImgInfo(CarImage carImage);

    List<Car> wxListCar(Car car);

    CarImage getCarMainImage(CarImage carImage);

    Car wxCarDetail(Car car);

    int wxCarAppoint(Appointment appointment);

    int changeMainImage(Car car);

    int delCarImage(@Param("delCarImageIds") String delCarImageIds);
}
