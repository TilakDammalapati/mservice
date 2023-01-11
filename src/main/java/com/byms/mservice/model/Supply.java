package com.byms.mservice.model;

import lombok.Data;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

@Data
public class Supply {

    private String productId;
    private String updateTimeStamp;

    private int quantity;

    public Supply(String productId,String date,int quantity) {
        this.updateTimeStamp = date;
        this.quantity =quantity;
        this.productId = productId;
    }
}
