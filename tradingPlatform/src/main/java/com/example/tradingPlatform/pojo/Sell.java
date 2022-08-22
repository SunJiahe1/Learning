package com.example.tradingPlatform.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class Sell {
    private Integer id;
    private String s_name;
    private Float s_price;
    private Integer s_amount;
    private Date s_date;
}
