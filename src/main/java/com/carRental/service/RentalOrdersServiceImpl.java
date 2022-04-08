package com.carRental.service;

import com.carRental.model.RentalOrders;
import com.carRental.repository.RentalOrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tomek on 12.12.2016.
 */
@Service
@Validated
@Transactional
public class RentalOrdersServiceImpl implements RentalOrdersService {

    private final RentalOrdersRepository repositoryRentalOrders;

    public RentalOrdersServiceImpl(RentalOrdersRepository repositoryRentalOrders) {
	this.repositoryRentalOrders = repositoryRentalOrders;
    }

    @Override
    public RentalOrders saveRentalOrder(RentalOrders rentalOrder) {
	return repositoryRentalOrders.save(rentalOrder);
    }

    @Override
    public List<RentalOrders> getRentalOrdersList() {
	return null;
    }

    @Override
    public RentalOrders getRentalOrderById(Integer rentalOrderId) {
	return repositoryRentalOrders.findOne(rentalOrderId);
    }

    @Override
    public List<RentalOrders> getRentalOrdersByCarId(Integer carId) {
	return repositoryRentalOrders.findByCarId(carId);
    }

    @Override
    public double getRentPrice(Date startRentDate, Date endRentDate, Integer pricePerDay) {
	long diff = endRentDate.getTime() - startRentDate.getTime();
	long rentalDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
	int i = (int) (long) rentalDays;
	double price = (double) pricePerDay;
	/*if (rentalDays >= 80) {
	    price = price - price * 0.6;
	} else {
	    for (int k = 1; k <= i; k++) {
		if (k % 5 == 0) {
		    price = price - price * 0.05;
		}
	    }
	}*/
	return (price * i);
    }

    @Override
    public void setOrderCancelledTrue(Integer rentalOrderId) {
	repositoryRentalOrders.getOne(rentalOrderId).setOrderStatus("Anulowane");
    }

    @Override
    public void deleteRentalOrder(Integer rentalOrderId) {
	repositoryRentalOrders.delete(rentalOrderId);
    }
}
