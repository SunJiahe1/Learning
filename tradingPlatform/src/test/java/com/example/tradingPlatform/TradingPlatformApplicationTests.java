package com.example.tradingPlatform;

import com.example.tradingPlatform.dao.TradingDataMapper;
import com.example.tradingPlatform.pojo.Purchase;
import com.example.tradingPlatform.pojo.Sell;
import com.example.tradingPlatform.pojo.User;
import com.example.tradingPlatform.service.RegisterService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class TradingPlatformApplicationTests {

	@Autowired
	TradingDataMapper tradingDataMapper;

	@Test
	void contextLoads() {
		Date date = new Date(); // this object contains the current date value
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(formatter.format(date));
		int result = tradingDataMapper.addPurchase(new Purchase(1,"leshi", 10.0F,10,date));
		int result2 = tradingDataMapper.addSell(new Sell(1,"adi",10.0F,10,date));
	}

	@Test
	void updata() {

	}

}
