package com.jason.controller;

import com.jason.model.User;
import com.jason.service.LoginService;
import com.jason.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private LoginService loginService;

    @RequestMapping("/doLogin")
    public String doLogin(User u, HttpServletRequest request, HttpServletResponse response){
        return loginService.doLogin(u,redisService,request,response);
    }

    @RequestMapping("/token/{cookieValue}")
    public Object checkLogin(@PathVariable("cookieValue") String cookieValue,String callback){

        String userString = loginService.checkLogin(redisService, cookieValue);
        if(null != userString && !"".equals(userString)){
            if(null != callback && !"".equals(callback)){
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userString);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }else{
                return userString;
            }
        }
        return null;
    }
}
