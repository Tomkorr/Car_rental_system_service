package com.carRental.service;

import com.carRental.model.Employees;

import java.util.List;

/**
 * Created by Dell on 20.03.2017.
 */
public interface EmployeesService {

    Employees saveEmployee(Employees employee);

    List<Employees> getEmployeesList();

    Employees getEmployeeById(Integer employeeId);
    
    Employees updateEmployee(Employees employee);

    void deleteEmployee(Integer employeeId);
}
