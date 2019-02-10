package com.llg.usedcar.service.impl;

import com.llg.usedcar.entity.WxJscode2Session;
import com.llg.usedcar.entity.WxUser;
import com.llg.usedcar.mapper.dao.WxUserMapper;
import com.llg.usedcar.service.interfaces.WxUserService;
import com.llg.usedcar.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class WxUserServiceImpl implements WxUserService {

    @Resource
    private WxUserMapper wxUserMapper;

    @Override
    public List<WxUser> listWxUser(WxUser wxUser) {
        return wxUserMapper.listUser(wxUser);
    }

    @Override
    public WxUser myBusinessCard(String openid) {
        return wxUserMapper.myBusinessCard(openid);
    }

    @Override
    public int updateBusinessCard(WxUser wxUser) {
        Date now = new Date();
        wxUser.setUpdateTime(now);
        if(wxUserMapper.myBusinessCard(wxUser.getOpenId()) != null){
            return wxUserMapper.updateBusinessCard(wxUser);
        }else{
            wxUser.setCreateTime(now);
            wxUser.setUserId(CommonUtils.generatePK());
            return wxUserMapper.saveBusinessCard(wxUser);
        }
    }

    @Override
    public WxUser wxUserByOpenid(String openid) {

        return wxUserMapper.wxUserByOpenid(openid);
    }

    @Override
    public int saveOrUpdateSession(WxJscode2Session wxSession) {
        WxJscode2Session session = wxUserMapper.getSessionByOpenid(wxSession);
        Date now = new Date();
        wxSession.setUpdateTime(now);
        //比微信的session_key晚10分钟过期
        wxSession.setExpiresAt(new Date(now.getTime() + (wxSession.getExpiresIn() + 600) * 1000));
        if(session != null){
            //更新
            return wxUserMapper.updateSession(wxSession);
        }else{
            //保存
            wxSession.setSessionId(CommonUtils.generatePK());
            wxSession.setCreateTime(now);
            wxSession.setSessionId(CommonUtils.generatePK());
            return wxUserMapper.saveSession(wxSession);
        }
    }

    @Override
    public WxJscode2Session getWxSession(String thirdSession) {
        return wxUserMapper.getWxSession(thirdSession);
    }
}
