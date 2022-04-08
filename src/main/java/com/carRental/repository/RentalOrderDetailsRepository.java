package com.carRental.repository;

import com.carRental.model.RentalOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dell on 21.03.2017.
 */
@Transactional
public interface RentalOrderDetailsRepository extends JpaRepository<RentalOrderDetails, Integer> {

    @Query(value = "Select count(*) from rental_order_details where order_status='Rozpoczęte'", nativeQuery = true)
    Integer countActiveOrders();
    
    @Query(value = "Select count(*) from rental_order_details where order_status='Do potwierdzenia'", nativeQuery = true)
    Integer countToConfirmOrders();

    @Query(value = "Select count(*) from rental_order_details where order_status='Zakończone'", nativeQuery = true)
    Integer countInactiveOrders();
    
    @Query(value = "Select count(*) from rental_order_details where order_status='Zarezerwowane'", nativeQuery = true)
    Integer countReservedOrders();
    
    @Query(value = "Select count(*) from rental_order_details where order_status='Anulowane'", nativeQuery = true)
    Integer countCancelledOrders();
    
    @Query(value = "Select count(*) from rental_order_details where order_status != 'Anulowane' and car_id = ?1", nativeQuery = true)
    Integer countOrdersByCarId(Integer carId);
    
    //@Query(value = "Select count(*) from rental_order_details where order_status != 'Anulowane' and employee_id = ?1", nativeQuery = true)
    //Integer countOrdersByEmployeeId(Integer employeeId);

    @Query(value = "Select * from rental_order_details where order_status='Rozpoczęte'", nativeQuery = true)
    List<RentalOrderDetails> findActiveOrders();
    
    @Query(value = "Select * from rental_order_details where order_status='Do potwierdzenia'", nativeQuery = true)
    List<RentalOrderDetails> findToConfirmOrders();

    @Query(value = "Select * from rental_order_details where order_status='Zakończone'", nativeQuery = true)
    List<RentalOrderDetails> findInactiveOrders();
    
    @Query(value = "Select * from rental_order_details where order_status='Zarezerwowane'", nativeQuery = true)
    List<RentalOrderDetails> findReservedOrders();
    
    @Query(value = "Select * from rental_order_details where order_status='Anulowane'", nativeQuery = true)
    List<RentalOrderDetails> findCancelledOrders();

    List<RentalOrderDetails> findByCarIdAndOrderStatus(Integer carId, String orderStatus);
    
    @Query(value = "Select * from rental_order_details where car_id=?1 and(order_status='Zarezerwowane' or order_status='Do potwierdzenia')", nativeQuery = true)
    List<RentalOrderDetails> findByCarIdAndZarezerwowaneDoPotwierdznia(Integer carId);
}
