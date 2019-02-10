package com.llg.usedcar.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.llg.usedcar.entity.Appointment;
import com.llg.usedcar.entity.Car;
import com.llg.usedcar.entity.CarImage;
import com.llg.usedcar.entity.ImageCutData;
import com.llg.usedcar.entity.vo.PageQO;
import com.llg.usedcar.result.Result;
import com.llg.usedcar.service.interfaces.CarService;
import com.llg.usedcar.service.interfaces.ShopService;
import com.llg.usedcar.utils.CommonUtils;
import com.llg.usedcar.utils.ImageUtils;
import com.llg.usedcar.utils.UploadUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@RestController
public class CarController {
    @Resource
    private CarService carService;
    @Resource
    private ShopService shopService;


    @GetMapping("/getImage")
    public void downloadLocal(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String url = request.getParameter("url");
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            ips = new FileInputStream(new File(url));
            //response.setContentType("multipart/form-data");
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (FileNotFoundException fe){
            fe.printStackTrace();
        }finally {
            if(out != null) out.close();
            if(ips != null) ips.close();
        }
    }

    @PostMapping("/uploadCarImag")
    public Result upload(@RequestParam("file") MultipartFile file, ImageCutData imageCutData, HttpServletRequest request) {
        try {
            String fileABSPath = UploadUtils.saveTempFile(file);//临时文件保存
            BufferedImage bi = ImageIO.read(new File(fileABSPath));
            bi = ImageUtils.cropImage(bi, imageCutData.getX(), imageCutData.getY(), imageCutData.getWidth(), imageCutData.getHeight());
            String filePath = UploadUtils.saveFile(bi, request);//保存处理后的文件
            Long carId = Long.parseLong(request.getParameter("carId"));
            Date now  = new Date();
            CarImage carImage = new CarImage(CommonUtils.generatePK(),carId ,
                    filePath, "0" , now, now );
            carService.saveCarImgInfo(carImage);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.buildBaseFail("上传失败！未知错误");
        }

        return Result.buildBaseSuccess("上传成功！");
    }

    /**
     * 新增车辆
     * @param car
     * @return
     */
    @PostMapping("addCar")
    public Result addCar(Car car, HttpServletRequest request){
        car.setCarId(CommonUtils.generatePK());
        car.setCarIndex(0);
        car.setShopId(CommonUtils.sessionUser(request).getShopId());
        car.setCarStatus(1);//待生效
        Date now = new Date();
        car.setCreateTime(now);
        car.setUpdateTime(now);
        carService.addCar(car);
        return Result.buildBaseSuccess();
    }

    /**
     * 修改车辆
     * 修改基本信息，宣传语，排序
     * @param car
     * @return
     */
    @PostMapping("updateCar")
    public Result updateCar(Car car){
        car.setUpdateTime(new Date());
        carService.updateCar(car);
        return Result.buildBaseSuccess();
    }

    /**
     * 发布车辆
     * @param car
     * @return
     */
    @PostMapping("publishCar")
    public Result publishCar(Car car){
        car.setUpdateTime(new Date());
        carService.publishCar(car);
        return Result.buildBaseSuccess();
    }

    /**
     * 撤销发布
     * @param car
     * @return
     */
    @PostMapping("revokeCar")
    public Result revokeCar(Car car){
        car.setUpdateTime(new Date());
        carService.revokeCar(car);
        return Result.buildBaseSuccess();
    }

    /**
     * 车辆图片列表
     * @param car
     * @return
     */
    @GetMapping("carImageList")
    public Result carImageList(Car car){
        return Result.buildBaseSuccess(carService.carImageList(car.getCarId() + ""));
    }

    /**
     * 修改车辆主图
     * @param car
     * @return
     */
    @PostMapping("/changeMainImage")
    public Result changeMainImage(Car car){
        return Result.buildBaseSuccess(carService.changeMainImage(car));
    }

    /**
     * 删除车辆图片
     * @param delCarImageIds
     * @return
     */
    @PostMapping("/delCarImage")
    public Result delCarImage(String delCarImageIds){
        return Result.buildBaseSuccess(carService.delCarImage(delCarImageIds));
    }

    /**
     * 后台车辆列表
     * @param car
     * @return
     */
    @GetMapping("bgListCar")
    public Result<Car> bgListCar(PageQO pageQO, Car car, HttpServletRequest request){
        car.setShopId(CommonUtils.sessionUser(request).getShopId());
        Page<Car> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        List<Car> cars = carService.bgCarList(car);
        if(cars.size() > 0){
            StringBuilder sb = new StringBuilder();
            for(Car c : cars){
                sb.append(c.getCarId());
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            List<CarImage> carImages = carService.carImageList(sb.toString());
            setCarImage(cars, carImages);
        }
        return Result.buildPageSuccess(page, cars);
    }

    /**
     * 小程序车辆列表
     * @param car
     * @return
     */
    @GetMapping("/wx/wxListCar")
    public Result<Car> wxListCar(PageQO pageQO, Car car, HttpServletRequest request){
        car.setShopId(CommonUtils.getShopInfo(request).getShopId());
        Page<Car> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        List<Car> cars = carService.wxListCar(car);
        return Result.buildPageSuccess(page, cars);
    }

    /**
     * 小程序车辆详情
     * @param car
     * @return
     */
    @GetMapping("/wx/wxCarDetail")
    public Result wxCarDetail(Car car, HttpServletRequest request){
        Long userId = CommonUtils.wxSessionUser(request).getUserId();
        car.setUserId(userId);
        return Result.buildBaseSuccess(carService.wxCarDetail(car));
    }

    private void setCarImage(List<Car> carList, List<CarImage> carImageList){
        for(Car c :carList){
            List<CarImage> carImages = new ArrayList<>();
            c.setCarImages(carImages);
            for (CarImage cm : carImageList){
                if(c.getCarId().equals(cm.getCarId())){
                    carImages.add(cm);
                    if(cm.getIsMainImg().equals("1")){
                        c.setMainImageId(cm.getCarImageId());
                    }
                }
            }
        }
    }


}
