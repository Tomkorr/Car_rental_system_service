package com.carRental.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Dell on 21.03.2017.
 */
@Entity(name = "rental_order_details")
public class RentalOrderDetails {

    @Id
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "car_id", nullable = false)
    private Integer carId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "employee_id_reserve", nullable = false)
    private Integer employeeIdReserve;
    
    @Column(name = "employee_id_receive", nullable = false)
    private Integer employeeIdReceive;
    
    @Column(name = "employee_id_let_out", nullable = false)
    private Integer employeeIdLetOut;
    
    @Column(name = "employee_id_cancelled", nullable = false)
    private Integer employeeIdCancelled;
    
    @Column(name = "cancel_reason", nullable = false)
    private String cancelReason;

    @Column(name = "cust_first_name", nullable = false)
    private String custFirstName;

    @Column(name = "cust_last_name", nullable = false)
    private String custLastName;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

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

    private RentalOrderDetails(){}

    public RentalOrderDetails(Integer orderId, Integer carId, Integer customerId, Integer employeeId, String emplFirstName, String emplLastName,
            String custFirstName, String custLastName, String brand, String model, String orderStatus, double rentalCost, Date rentStartDate,
            Date rentEndDate) {
        this.orderId = orderId;
        this.carId = carId;
        this.customerId = customerId;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.brand = brand;
        this.model = model;
        this.orderStatus = orderStatus;
        this.rentalCost = rentalCost;
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentEndDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getEmployeeIdReserve() {
	return employeeIdReserve;
    }

    public void setEmployeeIdReserve(Integer employeeIdReserve) {
	this.employeeIdReserve = employeeIdReserve;
    }

    public Integer getEmployeeIdReceive() {
	return employeeIdReceive;
    }

    public void setEmployeeIdReceive(Integer employeeIdReceive) {
	this.employeeIdReceive = employeeIdReceive;
    }

    public Integer getEmployeeIdLetOut() {
	return employeeIdLetOut;
    }

    public void setEmployeeIdLetOut(Integer employeeIdLetOut) {
	this.employeeIdLetOut = employeeIdLetOut;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
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
