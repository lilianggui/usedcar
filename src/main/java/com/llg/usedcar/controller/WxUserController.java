package com.llg.usedcar.controller;

import com.llg.usedcar.entity.WxJscode2Session;
import com.llg.usedcar.entity.WxUser;
import com.llg.usedcar.result.Result;
import com.llg.usedcar.service.interfaces.WxUserService;
import com.llg.usedcar.utils.CommonConst;
import com.llg.usedcar.utils.CommonUtils;
import com.llg.usedcar.utils.WxUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class WxUserController {

    private static final Logger logger = LoggerFactory.getLogger(WxUtils.class);

    @Resource
    private WxUserService wxUserService;

    @GetMapping("listWxUser")
    public Result listWxUser(WxUser wxUser){
        return Result.buildBaseSuccess(wxUserService.listWxUser(wxUser));
    }

    @GetMapping("/wx/myBusinessCard")
    public Result myBusinessCard(HttpServletRequest request){
        String openid = ((WxJscode2Session)request.getAttribute(CommonConst.WX_REQ_ATTR_SESSION_KEY)).getOpenid();
        WxUser wxUser = wxUserService.myBusinessCard(openid);
        return Result.buildBaseSuccess(wxUser);
    }

    @PostMapping("/wx/updateBusinessCard")
    public Result updateBusinessCard(WxUser wxUser, HttpServletRequest request){
        wxUser.setOpenId(CommonUtils.wxSessionUser(request).getOpenid());
        wxUser.setShopId(CommonUtils.getShopInfo(request).getShopId());
        return Result.buildBaseSuccess(wxUserService.updateBusinessCard(wxUser));
    }

    @PostMapping("/wx/login")
    public Result login(@RequestParam("jsCode") String jsCode, @RequestParam String appid){
        try {
            WxJscode2Session wxSession = WxUtils.getWxJSCodeSession(jsCode, appid);
            if(wxSession != null){
                String thirdSession = Md5Crypt.md5Crypt((wxSession.getSessionKey() + wxSession.getOpenid()).getBytes()
                        , "$1$" + UUID.randomUUID().toString().substring(0, 8));
                wxSession.setCryptSessionKey(thirdSession);
                wxUserService.saveOrUpdateSession(wxSession);
                Map<String, Object> result = new HashMap<>();
                result.put("third_Session", thirdSession);
                return Result.buildBaseSuccess(result);
            }else {
                logger.error("获取微信session失败，登陆失败");
            }

        } catch (Exception e) {
            logger.error("获取微信session失败");
            e.printStackTrace();
        }
        return Result.buildBaseFail("登陆失败");
    }

}
