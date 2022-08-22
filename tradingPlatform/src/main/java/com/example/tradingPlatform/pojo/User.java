package com.example.tradingPlatform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String user_name;
    private String password;
    private Float balance;
}
