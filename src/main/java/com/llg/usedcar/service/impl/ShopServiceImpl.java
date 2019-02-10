package com.llg.usedcar.service.impl;

import com.llg.usedcar.entity.Shop;
import com.llg.usedcar.entity.ShopAppInfo;
import com.llg.usedcar.mapper.dao.ShopMapper;
import com.llg.usedcar.service.interfaces.ShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Resource
    private ShopMapper shopMapper;

    @Override
    public List<Shop> getShopByShopId(Long shopId) {
        return shopMapper.getShopByShopId(shopId);
    }

    @Override
    public int updateShop(Shop shop) {
        return shopMapper.updateShop(shop);
    }

    @Override
    public Shop getWxShopInfo(String appid) {
        return shopMapper.getWxShopInfo(appid);
    }

    @Override
    public ShopAppInfo getShopAppInfo(String appid) {
        return shopMapper.getShopAppInfo(appid);
    }
}
