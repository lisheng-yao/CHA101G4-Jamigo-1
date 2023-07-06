package com.jamigo.shop.counter_order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EditCounterOrderDTO {

    private List<EditOrderDetailDTO> editOrderDetailDTOList;
}
