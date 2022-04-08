package com.carRental.service;

import com.carRental.model.Addresses;
import com.carRental.repository.AddressesRepository;
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
public class AddressesServiceImpl implements AddressesService {
    
    private final AddressesRepository repositoryAddresses;

    public AddressesServiceImpl(AddressesRepository repositoryAddresses) {
        this.repositoryAddresses = repositoryAddresses;
    }

    
    @Override
    public Addresses saveAddress(Addresses address) {
        return null;
    }

    @Override
    public List<Addresses> getAddresssList() {
        return null;
    }

    @Override
    public Addresses getAddressById(Integer addressId) {
        return null;
    }

    @Override
    public void deleteAddress(Integer addressId) {

    }

    @Override
    public Addresses updateAddress(Addresses address) {
        Addresses addressToUpdate = repositoryAddresses.findOne(address.getAddressId());
        
        addressToUpdate.setCity(address.getCity());
        addressToUpdate.setStreet(address.getStreet());
        addressToUpdate.setStreetNumber(address.getStreetNumber());
        addressToUpdate.setZipCode(address.getZipCode());
        
        return addressToUpdate;
    }
}
