package com.llg.usedcar.utils;

import com.alibaba.fastjson.JSONObject;
import com.llg.usedcar.entity.ShopAppInfo;
import com.llg.usedcar.entity.WxJscode2Session;
import com.llg.usedcar.service.interfaces.ShopService;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class WxUtils {
    private static final Logger logger = LoggerFactory.getLogger(WxUtils.class);


    @Autowired
    private ShopService ss;

    private static ShopService shopService;

    @PostConstruct
    public void init(){
        shopService = ss;
    }

    public static WxJscode2Session getWxJSCodeSession(String code, String appid) throws Exception {
        ShopAppInfo shopAppInfo = shopService.getShopAppInfo(appid);
        Map<String, String> query = new HashMap<>();
        query.put("js_code", code);
        query.put("appid", shopAppInfo.getAppid());
        query.put("secret", shopAppInfo.getSecret());
        query.put("grant_type", "authorization_code");
        HttpResponse response = HttpUtils.doGet("https://api.weixin.qq.com", "/sns/jscode2session", null, query);
        String responseStr = EntityUtils.toString(response.getEntity(), "UTF8");
        JSONObject json = JSONObject.parseObject(responseStr);
        WxJscode2Session session = null;
        logger.error(json.toJSONString());
        if(json.get("session_key") != null){
            session = new WxJscode2Session();
            session.setSessionKey((String)json.get("session_key"));
            session.setOpenid((String)json.get("openid"));
            if(json.get("expires_in") != null){
                session.setExpiresIn(Long.parseLong(json.get("expires_in").toString()));
            }else{
                session.setExpiresIn(7200L);
            }
        }else{
            logger.error("获取wxsession失败");
            logger.error(json.toJSONString());
        }
        return session;
    }


}
