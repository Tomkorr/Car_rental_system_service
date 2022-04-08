package com.carRental.repository;

import com.carRental.model.RentalOrderDetails;
import com.carRental.model.RentalOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Tomek on 12.12.2016.
 */
@Transactional
public interface RentalOrdersRepository extends JpaRepository<RentalOrders, Integer> {

    @Query(value = "Select * from rental_orders where car_id = ?1", nativeQuery = true)
    List<RentalOrders> findByCarId(Integer carId);

    @Query(value = "Select * from rental_orders where car_id = ?1 and order_status='Rozpoczęte'", nativeQuery = true)
    RentalOrders findByCarIdAndActive(Integer carId);
    
    @Modifying
    @Query(value = "update rental_orders set order_status = ?1 where order_id = ?2", nativeQuery = true)
    void setStatus(String status, Integer orderId);

    List<RentalOrders> findByCarIdAndOrderStatus(Integer carId, String orderStatus);
    
    List<RentalOrders> findByCustomerId(Integer customerId);
    
    @Modifying
    @Query(value = "delete from rental_orders where customer_id = ?1", nativeQuery = true)
    void deleteOrdersByCustomerId(Integer customerId);
    
    @Modifying
    @Query(value = "delete from rental_orders where car_id = ?1", nativeQuery = true)
    void deleteOrdersByCarId(Integer carId);
}
