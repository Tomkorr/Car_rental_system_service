package com.carRental.service;

import com.carRental.model.Customers;

import java.util.List;

/**
 * Created by Tomek on 11.12.2016.
 */
public interface CustomersService {

    Customers saveCustomer(Customers customer);

    List<Customers> getCustomersList();

    Customers getCustomerById(Integer customerId);

    void deleteCustomer(Integer customerId);
}
