package com.llg.usedcar.controller;

import com.llg.usedcar.entity.BgUser;
import com.llg.usedcar.entity.WxUser;
import com.llg.usedcar.result.Message;
import com.llg.usedcar.result.Result;
import com.llg.usedcar.service.interfaces.BgUserService;
import com.llg.usedcar.utils.CommonConst;
import com.llg.usedcar.utils.CommonUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BgLoginController {

    @Resource
    private BgUserService bgUserService;

    @PostMapping("login")
    public Result login(BgUser bgUser, HttpServletRequest request){
        BgUser user = bgUserService.selectUserByUsername(bgUser.getUsername());
        if(user == null){
            return Result.buildBaseFail(Message.UNKNOWN_ACCOUNT);//用户名不存在
        }
        if(!StringUtils.isEmpty(user.getPassword()) && user.getPassword().equals(bgUser.getPassword())){
            request.getSession().setAttribute(CommonConst.SESSION_KEY, user);
            return Result.buildBaseSuccess();
        }else{
            return Result.buildBaseFail(Message.INCORRECT_PASSWORD);//密码不正确
        }

    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().removeAttribute(CommonConst.SESSION_KEY);
        return Result.buildBaseSuccess();

    }

    @GetMapping("/isLogin")
    public Result isLogin(HttpServletRequest request){
        BgUser user = CommonUtils.sessionUser(request);
        Map<String, Object> loginStatus = new HashMap<>();
        if(user != null){
            loginStatus.put("status", 0);//0已登录 1未登录
            loginStatus.put("msg", "已登录");
            loginStatus.put("username", user.getUsername());
        }else{
            loginStatus.put("status", 1);//0已登录 1未登录
            loginStatus.put("msg", "未登录");
            loginStatus.put("username", null);
        }
        return Result.buildBaseSuccess(loginStatus);

    }

    @GetMapping("/reLogin")
    public Result reLogin(){
        return Result.buildBaseFail(Message.RE_LOGIN);//重新登陆

    }

    @PostMapping("/updatePassword")
    public Result updatePassword(BgUser bgUser, HttpServletRequest request){
        BgUser sessionUser = CommonUtils.sessionUser(request);
        BgUser user = bgUserService.selectUserByUserId(sessionUser.getUserId());
        if(user.getPassword().equals(bgUser.getOldPassword())){
            bgUser.setUserId(sessionUser.getUserId());
            bgUserService.updatePassword(bgUser);
        }else{
            return Result.buildBaseFail(Message.INCORRECT_OLD_PASSWORD);//旧密码不正确
        }
        return Result.buildBaseSuccess();
    }

}
