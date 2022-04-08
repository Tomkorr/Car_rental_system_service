/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carRental.service;

import com.carRental.model.RentalOrderDetails;
import com.carRental.repository.CarsRepository;
import com.carRental.repository.RentalOrderDetailsRepository;
import com.carRental.repository.RentalOrdersRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dell
 */
@Component
public class OrdersScheduler {

    @Autowired
    private RentalOrderDetailsRepository ordersDetRepo;

    @Autowired
    private RentalOrdersRepository ordersRepo;
    
    @Autowired
    private CarsRepository carsRepo;

    @Scheduled(cron = "0 * * * * *")
    public void checkOrdersEnd() {
        List<RentalOrderDetails> activeOrders = ordersDetRepo.findActiveOrders();
        List<RentalOrderDetails> reservedOrders = ordersDetRepo.findReservedOrders();

        try {
            for (int i = 0; i < activeOrders.size(); i++) {
                Date now = new Date();
                LocalDate currentDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endDate = activeOrders.get(i).getRentEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                
                if(currentDate.isAfter(endDate.minusDays(1))){
                    ordersRepo.setStatus("Do potwierdzenia", activeOrders.get(i).getOrderId());
                }
            }

            for (int i = 0; i < reservedOrders.size(); i++) {
                Date now = new Date();
                LocalDate currentDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate startDate = reservedOrders.get(i).getRentStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                System.out.println(currentDate + "  " + startDate);
                if(currentDate.compareTo(startDate) > 0 || currentDate.equals(startDate)){
                    ordersRepo.setStatus("Do potwierdzenia", reservedOrders.get(i).getOrderId());
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
