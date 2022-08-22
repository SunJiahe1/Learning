package com.example.tradingPlatform.dao;
import com.example.tradingPlatform.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TradingDataMapper {
    //获取管理员列表
    List<Administrator> getAdministratorsList();
    //获取用户列表
    List<User> userList();
    //获取用户
    User getUser(String user_name);
    //获取用户ID
    int getUserId(String user_name);
    //获取用户名
    String getUserName(int id);
    //获取用户最大id
    int getUserMaxId();
    //获取用户密码
    String getPassword(String user_name);
    //获取用户余额
    int getBalance(String user_name);
    //更新用户
    int updateUser(Map map);
    //获取用户买入
    List<Purchase> getPurchases(int id);
    List<Purchase> getPurchase(Map map);
    //获取用户卖出
    List<Sell> getSells(int id);
    List<Sell> getSell(Map map);
    //增加用户
    int addUser(User user);
    //增加用户买入
    int addPurchase(Purchase purchase);
    //增加用户卖出
    int addSell(Sell sell);
    //删除用户买入
    int deletePurchase(Map map);
    //删除用户卖出
    int deleteSell(Map map);
    //更新用户买入
    int updatePurchase(Map map);
    //更新用户卖出
    int updateSell(Map map);
    //获取市场电子票券
    List<Sell> getAllSell();
    //获取官方市场电子票券
    List<OfficialMarket> getOfficialMarketList();
    OfficialMarket getOfficialMarket(String ticket_name);
    //增加官方市场出售电子票券
    int addOfficialMarket(OfficialMarket OfficialMarket);
    //删除官方市场出售电子票券
    int deleteOfficialMarket(String ticket_name);
    //更新官方市场出售电子票券
    int updateOfficialMarket(Map map);
    //上传题目
    int addExam(MultipleChoice multipleChoice);
    //获取剩余做题次数
    AnswerTimes getAnswerTimes(int id);
    //通过id获取题目
    MultipleChoice getMultipleChoice(int id);
    //获取题目列表
    List<MultipleChoice> getMultipleChoices();
    //修改剩余做题次数
    int updateAnswerTimes(Map map);
}
