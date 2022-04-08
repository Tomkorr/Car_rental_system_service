package com.carRental.repository;

import com.carRental.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Tomek on 11.12.2016.
 */
@Transactional
public interface CustomersRepository extends JpaRepository<Customers, Integer> {

    @Query(value = "Insert into customers(first_name, last_name, age, gender, phone, email, address_id, rental_status)" +
            "values(?1, ?2, ?3, ?4, ?5, ?6, ?7, false)", nativeQuery = true)
    Integer saveCustomer(String firstName, String lastName, Integer age, String gender, String phone, String email,
                    Integer addressId);
    @Query(value = "Select count(*) from customers c join rental_orders ro on(c.customer_id = ro.customer_id) where c.customer_id = ?1 and ro.order_status='Rozpoczęte'", nativeQuery = true)
    Integer countCustomerOrders(Integer customerId);
}
