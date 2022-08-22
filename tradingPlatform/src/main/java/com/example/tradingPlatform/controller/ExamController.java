package com.example.tradingPlatform.controller;

import com.example.tradingPlatform.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExamController {
    @Autowired
    ExamService examService;

    @RequestMapping("/getNumberOfTimes")
    @ResponseBody
    public String getNumberOfTimes(@RequestBody String req) {
        return examService.getNumberOfTimes();
    }

    @RequestMapping("/getMultipleChoices")
    @ResponseBody
    public String getMultipleChoices(@RequestBody String rea) {
        return examService.getMultipleChoices();
    }

    @RequestMapping("/getMultipleChoiceAnswer")
    @ResponseBody
    public String getMultipleChoiceAnswer(@RequestParam(value = "choose") String choose, @RequestParam(value = "multiple_choice_id") int multiple_choice_id) {
        return examService.getMultipleChoiceAnswer(choose, multiple_choice_id);
    }
}
