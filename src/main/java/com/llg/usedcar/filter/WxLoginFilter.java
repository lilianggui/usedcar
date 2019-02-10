package com.llg.usedcar.filter;

import com.llg.usedcar.entity.WxJscode2Session;
import com.llg.usedcar.result.Message;
import com.llg.usedcar.service.interfaces.WxUserService;
import com.llg.usedcar.utils.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@Component
@Order(1)
@WebFilter(urlPatterns = "/wx/*")
public class WxLoginFilter implements Filter {

    @Autowired
    private WxUserService wxUserService;

    private static String[] needLoginUrls = {
            "/wx/wxCarAppoint", "/wx/cancelAppoint", "/wx/changeAppointDate", "/wx/myAppointList",
            "/wx/wxCarDetail", "/wx/myBusinessCard", "/wx/updateBusinessCard"
    };


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/JavaScript; charset=utf-8");
        String reqPath = request.getServletPath();
        if(needLogin(reqPath)){
            //需要微信登陆
            String thirdSession = request.getParameter("third_session");
            OutputStream out = response.getOutputStream();
            if(thirdSession == null){
                out.write(buildFail(Message.NO_PARAM_THIRD_SESSION).getBytes());
            }else{
                WxJscode2Session session = wxUserService.getWxSession(thirdSession);
                if(session == null){
                    out.write(buildFail(Message.WX_RELOGIN).getBytes());
                }else if(session.getExpiresAt().before(new Date())){
                    out.write(buildFail(Message.WX_SESSION_EXPIRES).getBytes());
                }else{
                    request.setAttribute(CommonConst.WX_REQ_ATTR_SESSION_KEY, session);
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
            out.flush();
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public String buildFail(Message message){
        StringBuilder sb = new StringBuilder();
        sb.append("{code:'");
        sb.append(message.getCode());
        sb.append("',");
        sb.append("msg:'");
        sb.append(message.getMsg());
        sb.append("'}");
        return sb.toString();
    }

    private boolean needLogin(String reqPath){
        for(String str : needLoginUrls){
            if(reqPath.equals(str)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
