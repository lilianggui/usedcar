package com.llg.usedcar.service.interfaces;

import com.llg.usedcar.entity.WxJscode2Session;
import com.llg.usedcar.entity.WxUser;

import java.util.List;

public interface WxUserService {
    List<WxUser> listWxUser(WxUser wxUser);

    WxUser myBusinessCard(String openid);

    int updateBusinessCard(WxUser wxUser);

    WxUser wxUserByOpenid(String openid);

    int saveOrUpdateSession(WxJscode2Session wxSession);

    WxJscode2Session getWxSession(String thirdSession);
}
