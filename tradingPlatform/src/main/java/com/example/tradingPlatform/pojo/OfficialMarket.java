package com.example.tradingPlatform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfficialMarket {
    private String ticket_name;
    private Integer ticket_price;
    private Integer ticket_amount;
    private String seller;
}
