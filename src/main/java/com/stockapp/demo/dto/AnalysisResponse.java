package com.stockapp.demo.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AnalysisResponse {
    private String ticker;
    private float quantity;
    private float avgprice;
    private float profit;

}
