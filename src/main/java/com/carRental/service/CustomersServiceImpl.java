package com.carRental.service;

import com.carRental.model.Customers;
import com.carRental.repository.CustomersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Tomek on 11.12.2016.
 */

@Service
@Validated
@Transactional
public class CustomersServiceImpl implements CustomersService {

    private final CustomersRepository repositoryCustomers;

    public CustomersServiceImpl(CustomersRepository repositoryCustomers) {
        this.repositoryCustomers = repositoryCustomers;
    }

    @Override
    public Customers saveCustomer(Customers customer) {
        return repositoryCustomers.save(customer);
    }

    @Override
    public List<Customers> getCustomersList() {
        return repositoryCustomers.findAll();
    }

    @Override
    public Customers getCustomerById(Integer customerId) {
        return repositoryCustomers.findOne(customerId);
    }

    @Override
    public void deleteCustomer(Integer customerId) {

    }
}
