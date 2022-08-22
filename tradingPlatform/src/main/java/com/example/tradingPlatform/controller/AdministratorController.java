package com.example.tradingPlatform.controller;

import com.example.tradingPlatform.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;

    @RequestMapping("/getAdName")
    @ResponseBody
    public String getAdName(@RequestBody String req) {
        return administratorService.getAdName();
    }

    @RequestMapping("/addOfficialMarket")
    @ResponseBody
    public String addOfficialMarket(@RequestParam(value = "ticket_name") String ticket_name, @RequestParam(value = "ticket_price") int ticket_price,
                                    @RequestParam(value = "ticket_amount") int ticket_amount, @RequestParam(value = "seller") String seller) {
        return administratorService.addOfficialMarket(ticket_name, ticket_price, ticket_amount, seller);
    }

    @RequestMapping("/img")
    @ResponseBody
    public String addImg(MultipartFile photo) throws Exception {
        return administratorService.addImg(photo);
    }

//    @RequestMapping("/getImg")
//    @ResponseBody
//    public String getImg(@RequestParam(value = "imgName") String imgName) throws Exception {
//        return administratorService.getImg(imgName);
//    }

    @RequestMapping("/oDrop")
    @ResponseBody
    public String dropOfficialMarket(@RequestParam(value = "ticket_name") String ticket_name) {
        return administratorService.dropOfficialMarket(ticket_name);
    }

    @RequestMapping("/addExam")
    @ResponseBody
    public String addExam(@RequestParam(value = "multiple_choice_id") int id, @RequestParam(value = "multiple_choice_title") String title, @RequestParam(value = "multiple_choice_option_a") String option_a,
                          @RequestParam(value = "multiple_choice_option_b") String option_b, @RequestParam(value = "multiple_choice_option_c") String option_c,
                          @RequestParam(value = "multiple_choice_option_d") String option_d, @RequestParam(value = "multiple_choice_answer") String answer) {
        return administratorService.addExam(id, title, option_a, option_b, option_c, option_d, answer);
    }
}
