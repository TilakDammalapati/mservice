package com.byms.mservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSupplyUpdateResponse {

    private String productId;

    private String updateTimeStamp;

    private int quantity;


    private String status;


}
