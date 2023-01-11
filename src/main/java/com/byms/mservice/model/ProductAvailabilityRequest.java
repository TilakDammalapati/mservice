package com.byms.mservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProductAvailabilityRequest {

    private String storeNo;

    private String productId;

    private int reqQty;

    private Date reqDate;
}
