package com.byms.mservice.rest;


import com.byms.mservice.model.*;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class MserviceRest {

    @RequestMapping(value = "/updateSupply", method = RequestMethod.POST)
    @ResponseBody
    public ProductSupplyUpdateResponse updateProduct(@RequestBody Supply supply) {

       List<Supply> supplies = supplyData();
        Supply matchSupply = supplies.stream()
                .filter(s -> s.getProductId().equals(supply.getProductId()))
                .findFirst().get();

        System.out.println("Found product from list :" +  matchSupply);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");

        ZonedDateTime actual = ZonedDateTime.parse(matchSupply.getUpdateTimeStamp(), formatter);

        System.out.println("actual time: "+actual);
        ZonedDateTime updateT = ZonedDateTime.parse(supply.getUpdateTimeStamp(), formatter);

        System.out.println("update time: "+updateT);
        String status =  updateT.isAfter(actual) ? "Updated" : "Out Of Sync Update";



       return new ProductSupplyUpdateResponse(supply.getProductId(),supply.getUpdateTimeStamp(),
               supply.getQuantity()+ matchSupply.getQuantity(),status);

    }

    @RequestMapping(value = "/getProdAvailability", method = RequestMethod.POST)
    @ResponseBody
    public ProductAvailabilityResponse getProductAvailability(@RequestBody ProductAvailabilityRequest request) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Availability> availabilities = availabilityData();
        List<Capacity> capacities = capacityData();

        try {

            Runnable availabilityTask = () -> {
                try {
                    availabilities.stream().filter(a-> a.getStoreId().equals(request.getStoreNo())
                            && a.getDate().equals(request.getReqDate())).findFirst().get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            Runnable capacityTask = () -> {
                try {
                    capacities.stream().filter(a-> a.getStoreId().equals(request.getStoreNo())
                            && a.getDate().equals(request.getReqDate())).findFirst().get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            executorService.execute(availabilityTask);

            executorService.execute(capacityTask);


            executorService.shutdown();
        } catch (Exception e){

        }


        String status = "";

        return new ProductAvailabilityResponse(request.getStoreNo(),request.getProductId(),request.getReqQty(),
                request.getReqDate(),status);
    }

    private List<Supply>  supplyData() {
        List<Supply> supplies = new ArrayList<>();

        supplies.add(new Supply("Product1","2021-03-16T08:53:48.616Z",10));
        supplies.add(new Supply ("Product2","2021-03-16T08:59:48.616Z",5));
        supplies.add(new Supply ("Product3","2021-03-16T09:10:48.616Z",30));
        supplies.add(new Supply("Product4","2021-03-16T09:10:48.616Z",20));

        return supplies;
    }

    private List<Availability>  availabilityData() {
        List<Availability> availabilities = new ArrayList<>();

        availabilities.add(new Availability("Store001", "Prod1", new Date(2021 - 02 - 19), 1.0));
        availabilities.add(new Availability ("Store001", "Prod2", new Date(2021 - 02 - 20), 3.0));
        availabilities.add(new Availability ("Store001", "Prod2", new Date(2021 - 02 - 21), 0.0));

        return availabilities;
    }


    private List<Capacity>  capacityData() {
        List<Capacity> capacities = new ArrayList<>();

        capacities.add(new Capacity (" Store001", "Prod1", new Date(2021 - 02 - 19), 0.0));
        capacities.add(new Capacity  (" Store001", "Prod1", new Date(2021 - 02 - 20), 2.0));
        capacities.add(new Capacity  (" Store001", "Prod1", new Date(2021 - 02 - 21), 2.0));
         capacities.add(new Capacity(" Store001", "Prod1", new Date(2021 - 02 - 22), 0.0));
        return capacities;
    }
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        System.out.println(ZonedDateTime.parse("2021-03-16T08:49:48.616Z", formatter));
    }

}