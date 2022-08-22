package com.example.tradingPlatform.controller;

import com.example.tradingPlatform.service.RegisterService;
import com.example.tradingPlatform.service.UserDemoService;
import com.example.tradingPlatform.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    UserDemoService userDemoService;

    @RequestMapping("/register2")
    @ResponseBody
    public String register2(@RequestBody String req1) {
        return registerService.registerService(req1);
    }

    @RequestMapping("/register3")
    @ResponseBody
    public String register3(@RequestParam(value = "user_name") String user_name, @RequestParam(value = "password") String password) {
        registerService.registerService2(user_name, password);
        userDetailService.loadUserByUsername(user_name);

        return "ok";
    }
}
