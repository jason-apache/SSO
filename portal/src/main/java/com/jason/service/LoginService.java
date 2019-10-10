package com.jason.service;

import com.jason.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Value("${sso_base_url}")
    public String baseUrl;
    @Value("${sso_access_login_url}")
    public String loginUrl;
    @Value("${sso_token_url}")
    public String tokenUrl;
    @Value("${sso_login_url_param}")
    public String loginParam;
    @Value("${cookie_key}")
    public String cookieKey;

    public String checkLogin(String cookieValue) {
        // http://127.0.0.1:8082/token/redis的key
        return HttpClientUtil.doGet(baseUrl + tokenUrl + cookieValue);
    }
}
