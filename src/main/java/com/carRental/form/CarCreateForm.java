package com.carRental.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Tomek on 11.12.2016.
 */
public class CarCreateForm {

    private Integer carId;

    @NotNull
    @Size(min = 2, max = 60)
    private String brandForm;

    @NotNull
    @Size(min = 1, max = 60)
    private String modelForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String colorForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String engineForm;
    
    @NotNull
    @Size(min = 2, max = 60)
    private String typeForm;

    @NotNull
    private String transmissionForm;

    @NotNull
    private String fuelForm;

    @NotNull
    private Integer yearForm;

    @NotNull
    private Integer pricePerDayForm;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getBrandForm() {
        return brandForm;
    }

    public void setBrandForm(String brandForm) {
        this.brandForm = brandForm;
    }

    public String getModelForm() {
        return modelForm;
    }

    public void setModelForm(String modelForm) {
        this.modelForm = modelForm;
    }

    public String getColorForm() {
        return colorForm;
    }

    public void setColorForm(String colorForm) {
        this.colorForm = colorForm;
    }

    public String getEngineForm() {
        return engineForm;
    }

    public void setEngineForm(String engineForm) {
        this.engineForm = engineForm;
    }

    public String getTransmissionForm() {
        return transmissionForm;
    }

    public void setTransmissionForm(String transmissionForm) {
        this.transmissionForm = transmissionForm;
    }

    public String getFuelForm() {
        return fuelForm;
    }

    public void setFuelForm(String fuelForm) {
        this.fuelForm = fuelForm;
    }

    public Integer getYearForm() {
        return yearForm;
    }

    public void setYearForm(Integer yearForm) {
        this.yearForm = yearForm;
    }

    public Integer getPricePerDayForm() {
        return pricePerDayForm;
    }

    public void setPricePerDayForm(Integer pricePerDayForm) {
        this.pricePerDayForm = pricePerDayForm;
    }

    public String getTypeForm() {
        return typeForm;
    }

    public void setTypeForm(String typeForm) {
        this.typeForm = typeForm;
    }
    
    
}
