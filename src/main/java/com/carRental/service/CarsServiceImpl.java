package com.carRental.service;

import com.carRental.model.Cars;
import com.carRental.repository.CarsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Tomek on 11.12.2016.
 */

@Service
@Validated
@Transactional
public class CarsServiceImpl  implements CarsService {

    private final CarsRepository repositoryCars;

    @Inject
    public CarsServiceImpl(final CarsRepository repositoryCars) {
        this.repositoryCars = repositoryCars;
    }

    @Override
    public Cars saveCar(@NotNull @Valid final Cars car) {
        return repositoryCars.save(car);
    }

    @Override
    public List<Cars> getCarsList() {
        return repositoryCars.findAll();
    }

    @Override
    public Cars getCarById(Integer carId) {
        return repositoryCars.findOne(carId);
    }

    @Override
    public void setCarAvailableFalse(Integer carId) {
        repositoryCars.findOne(carId).setAvailable(false);
    }

    @Override
    public void setCarAvailableTrue(Integer carId) {
        repositoryCars.findOne(carId).setAvailable(true);
    }

    @Override
    public void deleteCar(Integer carId) {

    }

    @Override
    public void setCarInRepairTrue(Integer carId) {
	repositoryCars.findOne(carId).setInRepair(true);
    }

    @Override
    public void setCarInRepairFalse(Integer carId) {
	repositoryCars.findOne(carId).setInRepair(false);
    }

    @Override
    public void setCarOnReviewTrue(Integer carId) {
	repositoryCars.findOne(carId).setOnReview(true);
    }

    @Override
    public void setCarOnReviewFalse(Integer carId) {
	repositoryCars.findOne(carId).setOnReview(false);
    }

    @Override
    public void setCarIsRentedTrue(Integer carId) {
	repositoryCars.findOne(carId).setIsRented(true);
    }

}
