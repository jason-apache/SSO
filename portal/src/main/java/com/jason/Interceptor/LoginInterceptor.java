package com.jason.Interceptor;

import com.jason.service.LoginService;
import com.jason.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookieValue = CookieUtil.getCookieValue(request, loginService.cookieKey);

        if(null == cookieValue || "".equals(cookieValue)){
            System.out.println("*************"+loginService.baseUrl+loginService.loginUrl+loginService.loginParam+request.getRequestURL());
            response.sendRedirect(loginService.baseUrl+loginService.loginUrl+loginService.loginParam+request.getRequestURL());
        }else{
            String userString = loginService.checkLogin(cookieValue);
            if(null != userString && !"".equals(userString)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
