package com.carRental.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Tomek on 11.12.2016.
 */
public class CustomerCreateForm {

    private Integer customerId;

    private Integer addressId;

    @NotNull
    @Size(min = 2, max = 60)
    private String firstNameForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String lastNameForm;

    @NotNull
    private Integer ageForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String genderForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String phoneForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String emailForm;

    @NotNull
    @Size(min = 3, max = 60)
    private String cityForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String streetForm;

    @NotNull
    @Size(min = 1, max = 10)
    private String streetNumberForm;

    @NotNull
    @Size(min = 6, max = 6)
    private String zipCodeForm;

    public String getCityForm() {
        return cityForm;
    }

    public void setCityForm(String cityForm) {
        this.cityForm = cityForm;
    }

    public String getStreetForm() {
        return streetForm;
    }

    public void setStreetForm(String streetForm) {
        this.streetForm = streetForm;
    }

    public String getStreetNumberForm() {
        return streetNumberForm;
    }

    public void setStreetNumberForm(String streetNumberForm) {
        this.streetNumberForm = streetNumberForm;
    }

    public String getZipCodeForm() {
        return zipCodeForm;
    }

    public void setZipCodeForm(String zipCodeForm) {
        this.zipCodeForm = zipCodeForm;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getFirstNameForm() {
        return firstNameForm;
    }

    public void setFirstNameForm(String firstNameForm) {
        this.firstNameForm = firstNameForm;
    }

    public String getLastNameForm() {
        return lastNameForm;
    }

    public void setLastNameForm(String lastNameForm) {
        this.lastNameForm = lastNameForm;
    }

    public Integer getAgeForm() {
        return ageForm;
    }

    public void setAgeForm(Integer ageForm) {
        this.ageForm = ageForm;
    }

    public String getGenderForm() {
        return genderForm;
    }

    public void setGenderForm(String genderForm) {
        this.genderForm = genderForm;
    }

    public String getPhoneForm() {
        return phoneForm;
    }

    public void setPhoneForm(String phoneForm) {
        this.phoneForm = phoneForm;
    }

    public String getEmailForm() {
        return emailForm;
    }

    public void setEmailForm(String emailForm) {
        this.emailForm = emailForm;
    }
}
