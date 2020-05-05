package com.example.apachebeam.wordcount.model;

import java.io.Serializable;
import java.util.StringJoiner;

public class Account implements Serializable {
    private int accountNo;
    private String firstName;
    private String lastName;
    private int balance;
    private String openingDate;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;

    public Account(int accountNo, String firstName, String lastName, int balance, String openingDate, String addressLine1, String addressLine2, String city, String country) {
        this.accountNo = accountNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.openingDate = openingDate;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.country = country;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
