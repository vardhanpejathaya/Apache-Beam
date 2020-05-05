package com.bnym.beneficiary.owner.data.processor.model;

import java.io.Serializable;

public class BeneficiaryOwner implements Serializable {

    private String idOwner;
    private String boName;
    private String residentCountry;
    private String addressLine1;
    private String addressLine2;
    private String email;

    public BeneficiaryOwner(String idOwner, String boName, String residentCountry, String email) {
        this.idOwner = idOwner;
        this.boName = boName;
        this.residentCountry = residentCountry;
        this.email = email;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public String getBoName() {
        return boName;
    }

    public void setBoName(String boName) {
        this.boName = boName;
    }

    public String getResidentCountry() {
        return residentCountry;
    }

    public void setResidentCountry(String residentCountry) {
        this.residentCountry = residentCountry;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
