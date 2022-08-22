package com.example.tradingPlatform.service;

import com.example.tradingPlatform.dao.TradingDataMapper;
import com.example.tradingPlatform.pojo.OfficialMarket;
import com.example.tradingPlatform.pojo.Purchase;
import com.example.tradingPlatform.pojo.Sell;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserDemoService {
    @Autowired
    TradingDataMapper tradingDataMapper;

    public String getUserName() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        String user_name = SecurityContextHolder.getContext().getAuthentication().getName();
        json.accumulate("user_name", user_name);
        array.add(json);

        return array.toString();
    }

    public String getBalance() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        String user_name = SecurityContextHolder.getContext().getAuthentication().getName();
        int balance = tradingDataMapper.getBalance(user_name);

        json.accumulate("balance", balance);
        array.add(json);

        return array.toString();
    }

    public String getPurchases() {
        String user_name = SecurityContextHolder.getContext().getAuthentication().getName();

        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        int id = tradingDataMapper.getUserId(user_name);
        List<Purchase> purchases = tradingDataMapper.getPurchases(id);

        if (purchases.isEmpty()) {
            json.accumulate("p_name", "无");
        }else {
            for (Purchase purchase : purchases) {
                json.accumulate("p_name", purchase.getP_name());
                json.accumulate("p_price", purchase.getP_price());
                json.accumulate("p_amount", purchase.getP_amount());
                json.accumulate("p_date", purchase.getP_date().toString());
                json.accumulate("length",purchases.size());
            }
        }

        array.add(json);

        return array.toString();
    }

    public String getSells() {
        String user_name = SecurityContextHolder.getContext().getAuthentication().getName();

        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        int id = tradingDataMapper.getUserId(user_name);
        List<Sell> sells = tradingDataMapper.getSells(id);

        if (sells.isEmpty()) {
            json.accumulate("s_name", "无");
        }else {
            for (Sell sell : sells) {
                json.accumulate("s_name", sell.getS_name());
                json.accumulate("s_price", sell.getS_price());
                json.accumulate("s_amount", sell.getS_amount());
                json.accumulate("s_date", sell.getS_date().toString());
                json.accumulate("length",sells.size());
            }
        }

        array.add(json);

        return array.toString();
    }

    public String addlMarket(String ticket_name, int ticket_price, int ticket_amount, String seller) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.format(date);

        int result;
        int result2;

        HashMap map = new HashMap();
        map.put("id", tradingDataMapper.getUserId(seller));
        map.put("p_name", ticket_name);

        HashMap map11 = new HashMap();
        map11.put("id", tradingDataMapper.getUserId(seller));
        map11.put("s_name", ticket_name);

        if (ticket_amount == tradingDataMapper.getPurchase(map).get(0).getP_amount()) {
            HashMap deletePurchase = new HashMap();
            deletePurchase.put("id", tradingDataMapper.getUserId(seller));
            deletePurchase.put("p_name", ticket_name);
            if (tradingDataMapper.getSell(map11).isEmpty()) {
                result = tradingDataMapper.addSell(new Sell(tradingDataMapper.getUserId(seller), ticket_name, (float) ticket_price, ticket_amount, date));
            }else {
                HashMap map2 = new HashMap();
                map2.put("id", tradingDataMapper.getUserId(seller));
                map2.put("s_name", ticket_name);
                map2.put("s_price", ticket_price);
                map2.put("s_amount", (float) ticket_amount + tradingDataMapper.getSell(map11).get(0).getS_amount());
                map2.put("s_date", date);
                result = tradingDataMapper.updateSell(map2);
            }
            result2 = tradingDataMapper.deletePurchase(deletePurchase);
        }else {
            HashMap updatePurchase = new HashMap();
            updatePurchase.put("id", tradingDataMapper.getUserId(seller));
            updatePurchase.put("p_name", ticket_name);
            updatePurchase.put("p_amount", tradingDataMapper.getPurchase(map).get(0).getP_amount() - ticket_amount);
            if (tradingDataMapper.getSell(map11).isEmpty()) {
                result = tradingDataMapper.addSell(new Sell(tradingDataMapper.getUserId(seller), ticket_name, (float) ticket_price, ticket_amount, date));
            }else {
                HashMap map2 = new HashMap();
                map2.put("id", tradingDataMapper.getUserId(seller));
                map2.put("s_name", ticket_name);
                map2.put("s_price", ticket_price);
                map2.put("s_amount", (float) ticket_amount + tradingDataMapper.getSell(map11).get(0).getS_amount());
                map2.put("s_date", date);
                result = tradingDataMapper.updateSell(map2);
            }
            result2 = tradingDataMapper.updatePurchase(updatePurchase);
        }

        if (result <= 0 || result2 <= 0) {
            json.accumulate("index","false");
        }else {
            json.accumulate("index","ok");
        }

        array.add(json);

        return array.toString();
    }

    public String dropMarket(String user_name, String ticket_name, int ticket_price, int ticket_amount) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.format(date);

        HashMap map = new HashMap();
        map.put("id", tradingDataMapper.getUserId(user_name));
        map.put("s_name", ticket_name);

        int result = tradingDataMapper.deleteSell(map);

        int result2;

        HashMap map2 = new HashMap();
        map2.put("id", tradingDataMapper.getUserId(user_name));
        map2.put("p_name", ticket_name);

        if (tradingDataMapper.getPurchase(map2).isEmpty()) {
            result2 = tradingDataMapper.addPurchase(new Purchase(tradingDataMapper.getUserId(user_name), ticket_name, (float) ticket_price, ticket_amount, date));
        }else {
            HashMap updatePurchaseMap = new HashMap();
            updatePurchaseMap.put("id", tradingDataMapper.getUserId(user_name));
            updatePurchaseMap.put("p_name", ticket_name);
            updatePurchaseMap.put("p_price", (float) ticket_price);
            updatePurchaseMap.put("p_amount", ticket_amount + tradingDataMapper.getPurchase(map2).get(0).getP_amount());
            updatePurchaseMap.put("p_date", date);

            result2 = tradingDataMapper.updatePurchase(updatePurchaseMap);
        }

        if (result <= 0 || result2 <= 0) {
            json.accumulate("index","false");
        }else {
            json.accumulate("index","ok");
        }

        array.add(json);

        return array.toString();
    }
}
