package com.carRental.service;

import com.carRental.model.Cars;

import java.util.List;

/**
 * Created by Tomek on 11.12.2016.
 */
public interface CarsService {

    Cars saveCar(Cars car);

    List<Cars> getCarsList();

    Cars getCarById(Integer carId);

    void setCarAvailableFalse(Integer carId);

    void setCarAvailableTrue(Integer carId);
    
    void setCarIsRentedTrue(Integer carId);
    
    void setCarOnReviewTrue(Integer carId);
    
    void setCarOnReviewFalse(Integer carId);
    
    void setCarInRepairTrue(Integer carId);
    
    void setCarInRepairFalse(Integer carId);

    void deleteCar(Integer carId);

}
