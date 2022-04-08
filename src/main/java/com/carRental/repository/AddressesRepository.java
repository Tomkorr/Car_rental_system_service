package com.carRental.repository;

import com.carRental.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Tomek on 11.12.2016.
 */
@Transactional
public interface AddressesRepository extends JpaRepository<Addresses, Integer> {

    @Query(value = "insert into addresses (city, street, zip_code, street_number) "
            + "values(?1,?2,?3,?4) returning address_id", nativeQuery = true)
    Integer saveAddress(String city, String street, String zipCode, String street_number);
}
