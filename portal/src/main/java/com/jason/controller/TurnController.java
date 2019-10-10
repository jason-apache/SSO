package com.jason.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TurnController {

    @RequestMapping("/")
    public String turnIndexPage(){
        return "index";
    }

    @RequestMapping("/buy")
    public String turnBuyPage(){
        return "buy";
    }
}
