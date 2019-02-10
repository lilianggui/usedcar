package com.llg.usedcar.utils;


import com.llg.usedcar.entity.BgUser;
import com.llg.usedcar.entity.Shop;
import com.llg.usedcar.entity.WxJscode2Session;
import com.llg.usedcar.entity.WxUser;
import com.llg.usedcar.service.interfaces.ShopService;
import com.llg.usedcar.service.interfaces.WxUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Lange
 * @date 2018-12-13 15:31
 */
@Component
public class CommonUtils {

    private static SnowflakeIdWorker SW = new SnowflakeIdWorker(0, 0);

    @Autowired
    private ShopService ss;
    private static ShopService shopService;

    @PostConstruct
    public void init(){
        shopService = ss;
    }


    public static BgUser sessionUser(HttpServletRequest request){
        return (BgUser)request.getSession().getAttribute(CommonConst.SESSION_KEY);
    }

    public static WxJscode2Session wxSessionUser(HttpServletRequest request){
        return  (WxJscode2Session) request.getAttribute(CommonConst.WX_REQ_ATTR_SESSION_KEY);
    }

    public static Shop getShopInfo(HttpServletRequest request){
        String appid = request.getParameter("appid");
        if(StringUtils.isEmpty(appid)){
            throw new RuntimeException("appid不能为空");
        }
        return shopService.getWxShopInfo(appid);
    }


    /**
     * 生成主键
     * @return
     */
    public static Long generatePK(){
        return SW.nextId();
    }


    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if(ip.contains(",")){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
