package com.example.tradingPlatform.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class Purchase {
    private Integer id;
    private String p_name;
    private Float p_price;
    private Integer p_amount;
    private Date p_date;
}
