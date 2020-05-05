package com.bnym.demo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Result1Map implements Serializable {

    Map<String, List<Position>> positionMap;
    private String idOwner;
    private long accountNo;
    private String accountStatus;
    private String country;

    public Result1Map() {
    }

    public Result1Map(String idOwner, long accountNo, String accountStatus, String country, Map<String, List<Position>> positionMap) {
        this.idOwner = idOwner;
        this.accountNo = accountNo;
        this.accountStatus = accountStatus;
        this.country = country;
        this.positionMap = positionMap;
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

    public Map<String, List<Position>> getPositionMap() {
        return positionMap;
    }

    public void setPositionMap(Map<String, List<Position>> positionMap) {
        this.positionMap = positionMap;
    }

    @Override
    public String toString() {
        return "{" +
                "accountNo:" + accountNo +
                ", positionMap:" + positionMap +
                ", idOwner:'" + idOwner + '\'' +
                ", accountStatus:'" + accountStatus + '\'' +
                ", country:'" + country + '\'' +
                '}';
    }
}
