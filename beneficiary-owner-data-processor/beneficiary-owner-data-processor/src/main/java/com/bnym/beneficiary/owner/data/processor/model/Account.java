package com.bnym.beneficiary.owner.data.processor.model;

import java.io.Serializable;

public class Account implements Serializable {

    private String idOwner;
    private long accountNo;
    private String accountStatus;
    private String country;

    public Account(String idOwner, long accountNo, String accountStatus, String country) {
        this.idOwner = idOwner;
        this.accountNo = accountNo;
        this.accountStatus = accountStatus;
        this.country = country;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
