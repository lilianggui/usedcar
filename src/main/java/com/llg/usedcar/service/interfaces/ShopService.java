package com.llg.usedcar.service.interfaces;

import com.llg.usedcar.entity.Shop;
import com.llg.usedcar.entity.ShopAppInfo;
import com.llg.usedcar.result.Result;

import java.util.List;

public interface ShopService {

    List<Shop> getShopByShopId(Long userId);

    int updateShop(Shop shop);

    Shop getWxShopInfo(String appid);

    ShopAppInfo getShopAppInfo(String appid);
}
