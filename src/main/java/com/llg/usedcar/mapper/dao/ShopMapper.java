package com.llg.usedcar.mapper.dao;

import com.llg.usedcar.entity.Shop;
import com.llg.usedcar.entity.ShopAppInfo;

import java.util.List;

public interface ShopMapper {

    List<Shop> getShopByShopId(Long shopId);

    int updateShop(Shop shop);

    Shop getWxShopInfo(String appid);

    ShopAppInfo getShopAppInfo(String appid);
}
