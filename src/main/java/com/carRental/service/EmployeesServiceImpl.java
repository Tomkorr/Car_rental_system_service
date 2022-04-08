package com.carRental.service;

import com.carRental.model.Employees;
import com.carRental.repository.EmployeesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Dell on 20.03.2017.
 */
@Service
@Validated
@Transactional
public class EmployeesServiceImpl implements EmployeesService{

    private final EmployeesRepository repositoryEmployees;

    public EmployeesServiceImpl(EmployeesRepository repositoryEmployees) {
        this.repositoryEmployees = repositoryEmployees;
    }

    @Override
    public Employees saveEmployee(Employees employee) {
        return repositoryEmployees.save(employee);
    }

    @Override
    public List<Employees> getEmployeesList() {
        return repositoryEmployees.findAll();
    }

    @Override
    public Employees getEmployeeById(Integer employeeId) {
        return repositoryEmployees.findOne(employeeId);
    }
    
    @Override
    public Employees updateEmployee(Employees employee) {
        Employees employeerToUpdate = repositoryEmployees.findOne(employee.getEmployeeId());
        
        employeerToUpdate.setAddressId(employee.getAddressId());
        employeerToUpdate.setAge(employee.getAge());
        employeerToUpdate.setEmail(employee.getEmail());
        employeerToUpdate.setFirstName(employee.getFirstName());
        employeerToUpdate.setLastName(employee.getLastName());
        employeerToUpdate.setGender(employee.getGender());
        employeerToUpdate.setLogin(employee.getLogin());
        employeerToUpdate.setPhone(employee.getPhone());
        employeerToUpdate.setRoleName(employee.getRoleName());
        employeerToUpdate.setPassword(employee.getPassword());
        employeerToUpdate.setEnabled(true);
        
        return employeerToUpdate;
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        repositoryEmployees.delete(employeeId);
    }
}
