package com.example.tradingPlatform.controller;

import com.example.tradingPlatform.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MarketController {
    @Autowired
    MarketService marketService;

    @RequestMapping("/getMarketList")
    @ResponseBody
    public String getMarketList(@RequestBody String req) {
        return marketService.getMarketList();
    }

    @RequestMapping("/getOfficialMarketList")
    @ResponseBody
    public String getOfficialMarketList(@RequestBody String req) {
        return marketService.getOfficialMarketList();
    }

    @RequestMapping("/buyOfficialMarket")
    @ResponseBody
    public String buyOfficialMarket(@RequestParam(value = "user_name") String user_name, @RequestParam(value = "ticket_name") String ticket_name,
                            @RequestParam(value = "ticket_price") int ticket_price, @RequestParam(value = "ticket_amount") int ticket_amount,
                            @RequestParam(value = "seller") String seller) {
        return marketService.buyOfficialMarket(user_name, ticket_name, ticket_price, ticket_amount, seller);
    }

    @RequestMapping("/buyMarket")
    @ResponseBody
    public String buyMarket(@RequestParam(value = "user_name") String user_name, @RequestParam(value = "ticket_name") String ticket_name,
                            @RequestParam(value = "ticket_price") int ticket_price, @RequestParam(value = "ticket_amount") int ticket_amount,
                            @RequestParam(value = "seller") String seller) {
        return marketService.buyMarket(user_name, ticket_name, ticket_price, ticket_amount, seller);
    }
}
