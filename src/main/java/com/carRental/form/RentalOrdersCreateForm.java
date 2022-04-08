package com.carRental.form;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Tomek on 11.12.2016.
 */
public class RentalOrdersCreateForm {

    private Integer reservationId;

    private Integer customerId;

    private Integer carId;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date startRentalDateForm;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date endRentalDateForm;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Date getStartRentalDateForm() {
        return startRentalDateForm;
    }

    public void setStartRentalDateForm(Date startRentalDateForm) {
        this.startRentalDateForm = startRentalDateForm;
    }

    public Date getEndRentalDateForm() {
        return endRentalDateForm;
    }

    public void setEndRentalDateForm(Date endRentalDateForm) {
        this.endRentalDateForm = endRentalDateForm;
    }
}
