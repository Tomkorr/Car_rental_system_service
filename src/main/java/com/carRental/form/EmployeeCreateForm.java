package com.carRental.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Dell on 20.03.2017.
 */
public class EmployeeCreateForm {

    private Integer employeeId;

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
    @Size(min = 2, max = 60)
    private String cityForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String streetForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String streetNumberForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String zipCodeForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String roleNameForm;

    @NotNull
    @Size(min = 2, max = 60)
    private String login;

    @NotNull
    @Size(min = 2, max = 60)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public String getRoleNameForm() {
        return roleNameForm;
    }

    public void setRoleNameForm(String roleNameForm) {
        this.roleNameForm = roleNameForm;
    }
}
