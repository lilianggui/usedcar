package com.llg.usedcar.mapper.dao;

import com.llg.usedcar.entity.WxJscode2Session;
import com.llg.usedcar.entity.WxUser;

import java.util.List;

public interface WxUserMapper {

    List<WxUser> listUser(WxUser wxUser);

    WxUser myBusinessCard(String openid);

    int updateBusinessCard(WxUser wxUser);

    WxUser wxUserByOpenid(String openid);

    WxJscode2Session getSessionByOpenid(WxJscode2Session wxSession);

    int updateSession(WxJscode2Session wxSession);

    int saveSession(WxJscode2Session wxSession);

    WxJscode2Session getWxSession(String thirdSession);

    int saveBusinessCard(WxUser wxUser);
}
