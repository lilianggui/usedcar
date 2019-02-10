package com.llg.usedcar.filter;

import com.llg.usedcar.utils.CommonConst;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    private static String[] noLoginUrls = {
            "/login", "/reLogin", "/isLogin", "/getImage", "/login.html"
    };

    private static String[] noLoginUrlsEndWith = {
            ".css",".png",".jpg", "js"
    };


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String reqPath = request.getServletPath();
        if(reqPath.startsWith("/wx") || request.getSession().getAttribute(CommonConst.SESSION_KEY) != null || noLoginEndWith(reqPath) || noLogin(reqPath)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            if(reqPath.equals("/") || reqPath.equals("/index.html")){
                System.out.println(123);
                request.getRequestDispatcher("/login.html").forward(request, response);
            }else{
                response.sendRedirect("/reLogin");
            }

        }

    }

    private boolean noLogin(String reqPath){
        for(String str : noLoginUrls){
            if(reqPath.equals(str)){
                return true;
            }
        }
        return false;
    }
    private boolean noLoginEndWith(String reqPath){
        for(String str : noLoginUrlsEndWith){
            if(reqPath.endsWith(str)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
