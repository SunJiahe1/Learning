package com.example.tradingPlatform.controller;

import com.example.tradingPlatform.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonController {
    @Autowired
    CommonService commonService;

    @RequestMapping("/see")
    @ResponseBody
    public String see(@RequestParam(value = "ticket_name") String ticket_name, @RequestParam(value = "seller") String seller) {
        return commonService.see(ticket_name, seller);
    }
}
