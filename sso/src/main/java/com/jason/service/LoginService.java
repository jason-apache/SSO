package com.jason.service;

import com.jason.mapper.UserMapper;
import com.jason.model.User;
import com.jason.utils.CookieUtil;
import com.jason.utils.JSONUtil;
import com.jason.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;
    @Value("${cookie_key}")
    private String cookieName;

    public String doLogin(User user,RedisService redisService, HttpServletRequest request, HttpServletResponse response){
        User u = userMapper.selectUserByUserNameAndPassowrd(user);
        if(null != u){
            u.setPassword(null);
            String redisKey = u.getId()+ UUIDUtil.getUUID();
            String result = redisService.set(redisKey, JSONUtil.toJsonString(u));

            if("OK".equals(result.toUpperCase())){
                CookieUtil.setCookie(request,response,cookieName,redisKey,10);
                return "success";
            }
        }

        return null;
    }

    public String checkLogin(RedisService redisService,String cookieValue){
        return redisService.get(cookieValue);
    }
}
