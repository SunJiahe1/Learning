package com.example.tradingPlatform.service;

import com.example.tradingPlatform.dao.TradingDataMapper;
import com.example.tradingPlatform.pojo.OfficialMarket;
import com.example.tradingPlatform.pojo.Purchase;
import com.example.tradingPlatform.pojo.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MarketService {
    @Autowired
    TradingDataMapper tradingDataMapper;

    public String getMarketList() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        List<Sell> marketList = tradingDataMapper.getAllSell();

        if (marketList.isEmpty()) {
            json.accumulate("ticket_name", "无");
        }else {
            for (Sell sell : marketList) {
                json.accumulate("ticket_name", sell.getS_name());
                json.accumulate("ticket_price", sell.getS_price());
                json.accumulate("ticket_amount", sell.getS_amount());
                json.accumulate("seller", tradingDataMapper.getUserName(sell.getId()));
                json.accumulate("length", marketList.size());
            }
        }

        array.add(json);
        return array.toString();
    }

    public String getOfficialMarketList() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        List<OfficialMarket> marketList = tradingDataMapper.getOfficialMarketList();

        if (marketList.isEmpty()) {
            json.accumulate("ticket_name", "无");
        }else {
            for (OfficialMarket market : marketList) {
                json.accumulate("ticket_name", market.getTicket_name());
                json.accumulate("ticket_price", market.getTicket_price());
                json.accumulate("ticket_amount", market.getTicket_amount());
                json.accumulate("seller", market.getSeller());
                json.accumulate("length", marketList.size());
            }
        }

        array.add(json);
        return array.toString();
    }

    public String buyMarket(String user_name, String ticket_name, int ticket_price, int ticket_amount, String seller) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        int result;
        int result2;
        int result3;
        int result4;

        HashMap map11 = new HashMap();
        map11.put("id", tradingDataMapper.getUserId(seller));
        map11.put("s_name", ticket_name);

        HashMap map = new HashMap();
        map.put("id", tradingDataMapper.getUserId(seller));
        map.put("s_name", ticket_name);
        map.put("s_amount", tradingDataMapper.getSell(map11).get(0).getS_amount() - ticket_amount);

        List<Sell> sell = tradingDataMapper.getSell(map11);

        HashMap map2 = new HashMap();
        map2.put("id", tradingDataMapper.getUserId(seller));
        map2.put("balance", (tradingDataMapper.getUser(seller).getBalance() + ticket_amount * ticket_price));

        HashMap map3 = new HashMap();
        map3.put("id", tradingDataMapper.getUserId(user_name));
        map3.put("balance", (tradingDataMapper.getUser(user_name).getBalance() - ticket_amount * ticket_price));

        HashMap map4 = new HashMap();
        map4.put("id", tradingDataMapper.getUserId(user_name));
        map4.put("p_name", ticket_name);

        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.format(date);

        if (ticket_amount < sell.get(0).getS_amount()) {
            result = tradingDataMapper.updateSell(map);
            result2 = tradingDataMapper.updateUser(map2);
            result3 = tradingDataMapper.updateUser(map3);
        }else {
            result = tradingDataMapper.deleteSell(map11);
            result2 = tradingDataMapper.updateUser(map2);
            result3 = tradingDataMapper.updateUser(map3);
        }

        if (tradingDataMapper.getPurchase(map4).isEmpty()) {
            result4 = tradingDataMapper.addPurchase(new Purchase(tradingDataMapper.getUserId(user_name), ticket_name, (float) ticket_price, ticket_amount, date));
        }else {
            HashMap map5 = new HashMap();
            map5.put("id", tradingDataMapper.getUserId(user_name));
            map5.put("p_amount", tradingDataMapper.getPurchase(map4).get(0).getP_amount() + ticket_amount);
            map5.put("p_date", date);
            result4 = tradingDataMapper.updatePurchase(map5);
        }

        if (result <= 0 || result2 <= 0 || result3 <= 0 || result4 <= 0) {
            json.accumulate("index", "false");
        }else {
            json.accumulate("index", "ok");
        }

        array.add(json);
        return array.toString();
    }

    public String buyOfficialMarket(String user_name, String ticket_name, int ticket_price, int ticket_amount, String seller) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        int result;
        int result3;
        int result4;

        OfficialMarket officialMarket = tradingDataMapper.getOfficialMarket(ticket_name);

        HashMap map = new HashMap();
        map.put("ticket_name", ticket_name);
        map.put("ticket_amount", officialMarket.getTicket_amount() - ticket_amount);

        HashMap map3 = new HashMap();
        map3.put("id", tradingDataMapper.getUserId(user_name));
        map3.put("balance", (float) (tradingDataMapper.getBalance(user_name) - ticket_amount * ticket_price));

        HashMap map4 = new HashMap();
        map4.put("id", tradingDataMapper.getUserId(user_name));
        map4.put("p_name", ticket_name);

        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.format(date);

        if (ticket_amount < officialMarket.getTicket_amount()) {
            result = tradingDataMapper.updateOfficialMarket(map);
            result3 = tradingDataMapper.updateUser(map3);
        }else {
            result = tradingDataMapper.deleteOfficialMarket(ticket_name);
            result3 = tradingDataMapper.updateUser(map3);
        }

        if (tradingDataMapper.getPurchase(map4).isEmpty()) {
            result4 = tradingDataMapper.addPurchase(new Purchase(tradingDataMapper.getUserId(user_name), ticket_name, (float) ticket_price, ticket_amount, date));
        }else {
            HashMap map5 = new HashMap();
            map5.put("id", tradingDataMapper.getUserId(user_name));
            map5.put("p_amount", tradingDataMapper.getPurchase(map4).get(0).getP_amount() + ticket_amount);
            map5.put("p_date", date);
            result4 = tradingDataMapper.updatePurchase(map5);
        }

        if (result <= 0 || result3 <= 0 || result4 <= 0) {
            json.accumulate("index", "false");
        }else {
            json.accumulate("index", "ok");
        }

        array.add(json);
        return array.toString();
    }
}
