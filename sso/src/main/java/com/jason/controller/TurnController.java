package com.jason.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TurnController {

    @RequestMapping("turnLogin")
    public String turnLonginPage(HttpServletRequest request, ModelMap modelMap){
        String currentPage = request.getParameter("currentPage");
        if(null != currentPage && !"".equals(currentPage)){
            modelMap.addAttribute("currentPage",currentPage);
            return "login";
        }
        return "404";
    }
}
