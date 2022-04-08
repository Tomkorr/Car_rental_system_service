package com.carRental.model;

import javax.persistence.*;

/**
 * Created by Tomek on 10.12.2016.
 */
@Entity(name = "cars")
public class Cars {

    @Id
    @SequenceGenerator(sequenceName = "cars_car_id_seq", name = "cars_car_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cars_car_id_seq")
    @Column(name = "car_id", nullable = false)
    private Integer carId;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "engine", nullable = false)
    private String engine;

    @Column(name = "transmission", nullable = false)
    private String transmission;

    @Column(name = "fuel", nullable = false)
    private String fuel;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "available", nullable = false)
    private boolean available;

    @Column(name = "is_rented", nullable = false)
    private boolean isRented;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "is_reserved", nullable = false)
    private boolean reserved;

    @Column(name = "in_repair", nullable = false)
    private boolean inRepair;

    @Column(name = "on_review", nullable = false)
    private boolean onReview;

    @Column(name = "price_per_day", nullable = false)
    private Integer pricePerDay;

    @Column(name = "model", nullable = false)
    private String model;

    public Cars() {
    }

    public Cars(Integer carId, String brand, Integer year, String color, String engine, String transmission,
	    String fuel, boolean available, boolean isRented, Integer pricePerDay, String model, String type, boolean deleted, boolean reserved) {
	this.carId = carId;
	this.brand = brand;
	this.year = year;
	this.color = color;
	this.engine = engine;
	this.transmission = transmission;
	this.fuel = fuel;
	this.available = available;
	this.isRented = isRented;
	this.pricePerDay = pricePerDay;
	this.model = model;
	this.type = type;
	this.deleted = deleted;
	this.reserved = reserved;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public Integer getCarId() {
	return carId;
    }

    public void setCarId(Integer carId) {
	this.carId = carId;
    }

    public String getBrand() {
	return brand;
    }

    public void setBrand(String brand) {
	this.brand = brand;
    }

    public Integer getYear() {
	return year;
    }

    public void setYear(Integer year) {
	this.year = year;
    }

    public String getColor() {
	return color;
    }

    public void setColor(String color) {
	this.color = color;
    }

    public String getEngine() {
	return engine;
    }

    public void setEngine(String engine) {
	this.engine = engine;
    }

    public String getTransmission() {
	return transmission;
    }

    public void setTransmission(String transmission) {
	this.transmission = transmission;
    }

    public String getFuel() {
	return fuel;
    }

    public void setFuel(String fuel) {
	this.fuel = fuel;
    }

    public boolean isAvailable() {
	return available;
    }

    public void setAvailable(boolean available) {
	this.available = available;
    }

    public boolean isIsRented() {
	return isRented;
    }

    public void setIsRented(boolean isRented) {
	this.isRented = isRented;
    }

    public Integer getPricePerDay() {
	return pricePerDay;
    }

    public void setPricePerDay(Integer pricePerDay) {
	this.pricePerDay = pricePerDay;
    }

    public String getModel() {
	return model;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public boolean isDeleted() {
	return deleted;
    }

    public void setDeleted(boolean deleted) {
	this.deleted = deleted;
    }

    public boolean isReserved() {
	return reserved;
    }

    public void setReserved(boolean reserved) {
	this.reserved = reserved;
    }

    public boolean isInRepair() {
	return inRepair;
    }

    public void setInRepair(boolean inRepair) {
	this.inRepair = inRepair;
    }

    public boolean isOnReview() {
	return onReview;
    }

    public void setOnReview(boolean onReview) {
	this.onReview = onReview;
    }

}
