package com.carRental.service;

import com.carRental.model.RentalOrders;

import java.util.Date;
import java.util.List;
import org.joda.time.LocalDate;

/**
 * Created by Tomek on 12.12.2016.
 */
public interface RentalOrdersService {
    RentalOrders saveRentalOrder(RentalOrders rentalOrder);

    List<RentalOrders> getRentalOrdersList();

    RentalOrders getRentalOrderById(Integer rentalOrderId);

    List<RentalOrders>  getRentalOrdersByCarId(Integer carId);

    double getRentPrice(Date startRentDate, Date endRentDate, Integer pricePerDay);

    void setOrderCancelledTrue(Integer rentalOrderId);

    void deleteRentalOrder(Integer rentalOrderId);
}
