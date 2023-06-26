package com.jamigo.shop.platform_order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CounterOrderForPlatformOrderDTO {
    private Byte disbursementStat;
    private List<ProductForPlatformOrderDTO> product = new ArrayList<>();
}
