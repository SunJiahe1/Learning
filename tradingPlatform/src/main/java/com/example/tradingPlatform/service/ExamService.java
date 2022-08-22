package com.example.tradingPlatform.service;

import com.example.tradingPlatform.dao.TradingDataMapper;
import com.example.tradingPlatform.pojo.MultipleChoice;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class ExamService {
    @Autowired
    TradingDataMapper tradingDataMapper;

    public String getNumberOfTimes() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        String user_name = SecurityContextHolder.getContext().getAuthentication().getName();

        int number_of_times = tradingDataMapper.getAnswerTimes(tradingDataMapper.getUserId(user_name)).getNumber_of_times();

        json.accumulate("number_of_times", number_of_times);

        array.add(json);

        return array.toString();
    }

    public String getMultipleChoices() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        List<MultipleChoice> multipleChoiceList = tradingDataMapper.getMultipleChoices();

        int length = multipleChoiceList.size();

        Random rand = new Random();
        int n1 = rand.nextInt(length);

        json.accumulate("multiple_choice_id", multipleChoiceList.get(n1).getId());
        json.accumulate("multiple_choice_title", multipleChoiceList.get(n1).getTitle());
        json.accumulate("multiple_choice_option_a", multipleChoiceList.get(n1).getOption_a());
        json.accumulate("multiple_choice_option_b", multipleChoiceList.get(n1).getOption_b());
        json.accumulate("multiple_choice_option_c", multipleChoiceList.get(n1).getOption_c());
        json.accumulate("multiple_choice_option_d", multipleChoiceList.get(n1).getOption_d());

        array.add(json);

        return array.toString();
    }

    public String getMultipleChoiceAnswer(String choose, int multiple_choice_id) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        String user_name = SecurityContextHolder.getContext().getAuthentication().getName();

        String answer = tradingDataMapper.getMultipleChoice(multiple_choice_id).getAnswer();

        if (answer.equals(choose)) {
            HashMap map = new HashMap();
            map.put("id", tradingDataMapper.getUserId(user_name));
            map.put("balance", (tradingDataMapper.getUser(user_name).getBalance() + 10));

            HashMap map2 = new HashMap();
            map2.put("id", tradingDataMapper.getUserId(user_name));
            map2.put("number_of_times", tradingDataMapper.getAnswerTimes(tradingDataMapper.getUserId(user_name)).getNumber_of_times() - 1);

            int result = tradingDataMapper.updateUser(map);
            int result2 = tradingDataMapper.updateAnswerTimes(map2);

            json.accumulate("index", "ok");
        }else {
            HashMap map2 = new HashMap();
            map2.put("id", tradingDataMapper.getUserId(user_name));
            map2.put("number_of_times", tradingDataMapper.getAnswerTimes(tradingDataMapper.getUserId(user_name)).getNumber_of_times() - 1);
            int result2 = tradingDataMapper.updateAnswerTimes(map2);
            json.accumulate("index", "false");
        }

        array.add(json);

        return array.toString();
    }
}
