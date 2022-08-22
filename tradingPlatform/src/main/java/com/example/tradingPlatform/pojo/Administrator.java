package com.example.tradingPlatform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Administrator {
    private Integer id;
    private String administrator_name;
    private String password;
}
