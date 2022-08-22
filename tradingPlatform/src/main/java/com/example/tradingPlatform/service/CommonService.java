package com.example.tradingPlatform.service;

import com.example.tradingPlatform.dao.TradingDataMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class CommonService {
    @Autowired
    TradingDataMapper tradingDataMapper;

    public String see(String ticket_name, String seller) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        int result = 0;

        if (result <= 0) {
            json.accumulate("index","false");
        }else {
            json.accumulate("index","ok");
        }

        array.add(json);

        return array.toString();
    }
}
