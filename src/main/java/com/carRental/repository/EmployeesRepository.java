package com.carRental.repository;

import com.carRental.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dell on 20.03.2017.
 */
@Transactional
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    @Query
    Employees findByLogin(String login);

    @Query(value = "Select count(*) from employees", nativeQuery = true)
    Integer countEmployess();
}
