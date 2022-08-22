package com.example.tradingPlatform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MultipleChoice {
    private Integer id;
    private String title;
    private String option_a;
    private String option_b;
    private String option_c;
    private String option_d;
    private String answer;
}
