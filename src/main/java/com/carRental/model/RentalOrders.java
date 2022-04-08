package com.carRental.model;

import javax.persistence.*;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Tomek on 12.12.2016.
 */

@Entity(name = "rental_orders")
public class RentalOrders {

    @Id
    @SequenceGenerator(sequenceName="rental_orders_rental_order_id_seq", name="rental_orders_rental_order_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rental_orders_rental_order_id_seq")
    @Column(name = "order_id", nullable = false)
    private Integer rentalOrderId;

    @Column(name = "car_id", nullable = false)
    private Integer carId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;
    
    @Column(name = "employee_id_let_out", nullable = false)
    private Integer employeeIdLetOut;
    
    @Column(name = "employee_id_receive", nullable = false)
    private Integer employeeIdReceive;

    @Column(name = "employee_id_reserve", nullable = false)
    private Integer employeeIdReserve;

    @Column(name = "employee_id_cancelled", nullable = false)
    private Integer employeeIdCancelled;
    
    @Column(name = "cancel_reason", nullable = false)
    private String cancelReason;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_processed", nullable = false)
    private Date dateProcessed;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "rent_start_date", nullable = false, columnDefinition= "DATE")
    private Date rentStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "rent_end_date", nullable = false, columnDefinition= "DATE")
    private Date rentEndDate;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "rental_cost")
    private double rentalCost;
    
    @Column(name = "to_confirm")
    private Boolean toConfirm;
    
    

    public  RentalOrders(){}

    public RentalOrders(Integer rentalOrderId, Integer carId, Integer customerId, Integer employeeIdReserve, Integer employeeIdLetOut,
	    Integer employeeIdReceive, Integer employeeIdCancelled, String cancelReason, Date dateProcessed, Date rentStartDate, Date rentEndDate, String orderStatus, double rentalCost, Boolean toConfirm) { 
        this.rentalOrderId = rentalOrderId;
        this.carId = carId;
        this.customerId = customerId;
	this.employeeIdLetOut = employeeIdLetOut;
	this.employeeIdReceive = employeeIdReceive;
        this.employeeIdReserve = employeeIdReserve;
	this.employeeIdCancelled = employeeIdCancelled;
	this.cancelReason = cancelReason;
        this.dateProcessed = dateProcessed;
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentEndDate;
        this.orderStatus = orderStatus;
        this.rentalCost = rentalCost;
	this.toConfirm = toConfirm;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
    }

    public Integer getRentalOrderId() {
        return rentalOrderId;
    }

    public void setRentalOrderId(Integer rentalOrderId) {
        this.rentalOrderId = rentalOrderId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getEmployeeIdLetOut() {
	return employeeIdLetOut;
    }

    public void setEmployeeIdLetOut(Integer customerIdLetOut) {
	this.employeeIdLetOut = customerIdLetOut;
    }

    public Integer getEmployeeIdReceive() {
	return employeeIdReceive;
    }

    public void setEmployeeIdReceive(Integer employeeIdReceive) {
	this.employeeIdReceive = employeeIdReceive;
    }

    public Integer getEmployeeIdReserve() {
        return employeeIdReserve;
    }

    public void setEmployeeIdReserve(Integer employeeIdReserve) {
        this.employeeIdReserve = employeeIdReserve;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public Date getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public Date getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getToConfirm() {
	return toConfirm;
    }

    public void setToConfirm(Boolean toConfirm) {
	this.toConfirm = toConfirm;
    }

    public Integer getEmployeeIdCancelled() {
	return employeeIdCancelled;
    }

    public void setEmployeeIdCancelled(Integer employeeIdCancelled) {
	this.employeeIdCancelled = employeeIdCancelled;
    }

    public String getCancelReason() {
	return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
	this.cancelReason = cancelReason;
    }
    
}
