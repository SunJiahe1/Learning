package com.example.tradingPlatform.controller;

import com.example.tradingPlatform.service.UserDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserDemoController {
    @Autowired
    UserDemoService userDemoService;

    @RequestMapping("/getUserName")
    @ResponseBody
    public String getUserName(@RequestBody String req) {
        return userDemoService.getUserName();
    }

    @RequestMapping("/getBalance")
    @ResponseBody
    public String getBalance(@RequestBody String req) {
        return userDemoService.getBalance();
    }

    @RequestMapping("/getPurchases")
    @ResponseBody
    public String getPurchases(@RequestBody String req) {
        return userDemoService.getPurchases();
    }

    @RequestMapping("/getSells")
    @ResponseBody
    public String getSells(@RequestBody String req) {
        return userDemoService.getSells();
    }

    @RequestMapping("/addMarket")
    @ResponseBody
    public String addOfficialMarket(@RequestParam(value = "ticket_name") String ticket_name, @RequestParam(value = "ticket_price") int ticket_price,
                                    @RequestParam(value = "ticket_amount") int ticket_amount, @RequestParam(value = "seller") String seller) {
        return userDemoService.addlMarket(ticket_name, ticket_price, ticket_amount, seller);
    }

    @RequestMapping("/drop")
    @ResponseBody
    public String dropOfficialMarket(@RequestParam(value = "user_name") String user_name, @RequestParam(value = "ticket_name") String ticket_name,
                                     @RequestParam(value = "ticket_price") int ticket_price, @RequestParam(value = "ticket_amount") int ticket_amount) {
        return userDemoService.dropMarket(user_name, ticket_name, ticket_price, ticket_amount);
    }
}
