package com.byms.mservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Availability {

    private  String storeId;
    private String productId;
    private Date date;
    private Double availQty;
}
