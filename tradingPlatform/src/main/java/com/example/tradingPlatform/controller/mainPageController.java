package com.example.tradingPlatform.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class mainPageController {
//    @RequestMapping({"/", "/mainPage"})
//    public String mainPage() {
//        return "mainPage";
//    }
    @RequestMapping("/user")
    public String user() {
        return "/views/user";
    }
    @RequestMapping("/administration")
    public String administration() {
        return "/views/administration";
    }
    @RequestMapping("/market")
    public String market() {
        return "/views/market";
    }
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    @RequestMapping("/exam")
    public String exam() {
        return "/views/exam";
    }
}
