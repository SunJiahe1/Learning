package com.example.tradingPlatform.service;

import com.example.tradingPlatform.config.SecurityConfig;
import com.example.tradingPlatform.dao.TradingDataMapper;
import com.example.tradingPlatform.pojo.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {
    @Autowired
    TradingDataMapper tradingDataMapper;

    public String registerService(String req1) {
        int len = req1.length() - 2;
        char[] alist = new char[len];
        for (int i = 1;i < req1.length()-1;i++) {
            alist[i-1] = req1.charAt(i);
        }
        String req11 = new String(alist);

        List<User> userLIst = tradingDataMapper.userList();

        JSONArray userArr = new JSONArray();
        JSONObject userObj = new JSONObject();

        int index = 0;

        for (User user : userLIst) {
            if (req11.equals(user.getUser_name())) {
                index = 1;
                userObj.accumulate("index","no");
                break;
            }
        }
        if (index == 0) {
            userObj.accumulate("index","yes");
        }

        userArr.add(userObj);

        return userArr.toString();
    }

    public String registerService2(String user_name, String password) {
        int id = tradingDataMapper.getUserMaxId() + 1;
        int balance = 100;

        int result = tradingDataMapper.addUser(new User(id, user_name, password, (float) balance));

        if (result != 0) {
            return "ok";
        }else {
            return "false";
        }
    }
}
