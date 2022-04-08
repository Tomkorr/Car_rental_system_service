package com.carRental.model;

import javax.persistence.*;

/**
 * Created by Tomek on 11.12.2016.
 */

@Entity(name = "addresses")
public class Addresses {

    @Id
    @SequenceGenerator(sequenceName="addresses_address_id_seq", name="addresses_address_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "addresses_address_id_seq")
    @Column(name = "address_id", nullable = false)
    private Integer addressId;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "street_number", nullable = false)
    private String streetNumber;

    public Addresses(){};

    public Addresses(Integer addressId, String city, String street, String zipCode, String streetNumber) {
        this.addressId = addressId;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.streetNumber = streetNumber;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}
