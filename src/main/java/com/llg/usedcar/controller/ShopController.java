package com.llg.usedcar.controller;

import com.llg.usedcar.entity.CarImage;
import com.llg.usedcar.entity.Shop;
import com.llg.usedcar.result.Result;
import com.llg.usedcar.service.interfaces.ShopService;
import com.llg.usedcar.utils.CommonUtils;
import com.llg.usedcar.utils.UploadUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class ShopController {

    @Resource
    private ShopService shopService;

    /**
     * 后台获取店铺信息
     * @return
     */
    @GetMapping("getShopByUserId")
    public Result getShopByUserId(HttpServletRequest request){
        Long shopId = CommonUtils.sessionUser(request).getShopId();
        return  Result.buildBaseSuccess(shopService.getShopByShopId(shopId));
    }

    @PostMapping("/uploadShopImage")
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String fileABSPath = UploadUtils.saveFile(file, request);
        Long shopId = Long.parseLong(request.getParameter("shopId"));
        Shop shop = new Shop();
        shop.setShopId(shopId);
        shop.setShopImage(fileABSPath);
        shop.setUpdateTime(new Date());
        shopService.updateShop(shop);
        return Result.buildBaseSuccess("上传成功！");
    }

    /**
     * 小程序获取店铺信息
     * @return
     */
    @GetMapping("/wx/getWxShopInfo")
    public Result getWxShopInfo(HttpServletRequest request){
        return  Result.buildBaseSuccess(CommonUtils.getShopInfo(request));
    }



    @PostMapping("/updateShop")
    public Result updateShop(Shop shop){
        shopService.updateShop(shop);
        return  Result.buildBaseSuccess();
    }


}
