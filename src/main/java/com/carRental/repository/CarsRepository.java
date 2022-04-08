package com.carRental.repository;

import com.carRental.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;

/**
 * Created by Tomek on 10.12.2016.
 */
@Transactional
public interface CarsRepository extends JpaRepository<Cars, Integer> {

    @Query(value = "Select * from cars where available = true and type = 'Osobowy' and deleted = false", nativeQuery = true)
    List<Cars> findAvailablePassengerCars();
    
    @Query(value = "Select * from cars where on_review = true and type = 'Osobowy' and deleted = false", nativeQuery = true)
    List<Cars> findOnReviewPassengerCars();
    
    @Query(value = "Select * from cars where in_repair = true and type = 'Osobowy' and deleted = false", nativeQuery = true)
    List<Cars> findInRepairPassengerCars();

    @Query(value = "Select * from cars where available = false and type = 'Osobowy' and deleted = false", nativeQuery = true)
    List<Cars> findNoAvailablePassengerCars();

    @Query(value = "Select * from cars where available = true and type = 'Dostawczy' and deleted = false", nativeQuery = true)
    List<Cars> findAvailableDeliveryCars();

    @Query(value = "Select * from cars where available = false and type = 'Dostawczy' and deleted = false", nativeQuery = true)
    List<Cars> findNoAvailableDeliveryCars();

    @Query(value = "Select count(*) from cars where available = true and type = 'Osobowy' and deleted = false", nativeQuery = true)
    Integer countActivePassengerCars();
    
    @Query(value = "Select count(*) from cars where on_review = true and type = 'Osobowy' and deleted = false", nativeQuery = true)
    Integer countOnReviewPassengerCars();
    
    @Query(value = "Select count(*) from cars where in_repair = true and type = 'Osobowy' and deleted = false", nativeQuery = true)
    Integer countInRepairPassengerCars();

    @Query(value = "Select count(*) from cars where available = false and type = 'Osobowy' and deleted = false", nativeQuery = true)
    Integer countInactivePassengerCars();

    @Query(value = "Select count(*) from cars where available = true and type = 'Dostawczy' and deleted = false", nativeQuery = true)
    Integer countActiveDeliveryCars();

    @Query(value = "Select count(*) from cars where available = false and type = 'Dostawczy' and deleted = false", nativeQuery = true)
    Integer countInactiveDeliveryCars();

    @Query(value = "Select count(*) from cars where type = 'Dostawczy' and deleted = false", nativeQuery = true)
    Integer countDeliveryCars();

    @Query(value = "Select count(*) from cars where type = 'Osobowy' and deleted = false", nativeQuery = true)
    Integer countPassengerCars();

    @Query(value = "Insert into cars(brand, year, color, engine, transmission, fuel, available, price_per_day, model"
            + " values(?1, ?2, ?3, ?4 ,?5, ?6, true, ?7, ?8)", nativeQuery = true)
    Integer saveCar(String brand, Integer year, String color, String engine, String transmission, String fuel,
            Integer pricePerDay, String model);

    @Query(value = "Select * from cars c join rental_orders ro on(c.car_id = ro.car_id) where ro.customer_id = ?1 and ro.order_status='Rozpoczęte'", nativeQuery = true)
    List<Cars> findAllCustomerCars(Integer customerId);
    
    @Modifying
    @Query(value = "Update cars set deleted = true where car_id = ?1", nativeQuery = true)
    void carSetStatusDeletedTrue(Integer carId);
    
    @Modifying
    @Query(value = "Update cars set available = ?1 where car_id = ?2", nativeQuery = true)
    void carSetAvailable(Boolean available, Integer carId);
    
}
