package com.carRental.service;

import com.carRental.model.Addresses;

import java.util.List;

/**
 * Created by Tomek on 11.12.2016.
 */
public interface AddressesService {

    Addresses saveAddress(Addresses address);

    List<Addresses> getAddresssList();

    Addresses getAddressById(Integer addressId);
    
    Addresses updateAddress(Addresses address);

    void deleteAddress(Integer addressId);
}
