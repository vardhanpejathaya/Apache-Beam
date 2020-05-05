package com.bnym.beneficiary.owner.data.processor.model;

import java.io.Serializable;

public class Result1 implements Serializable {

    private String idOwner;
    private long accountNo;
    private String accountStatus;
    private String country;
    private String assetType;
    private long quantity;
    private String exchange;
    private String isin;
    private double unrealizedGain;

    public Result1() {
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

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public double getUnrealizedGain() {
        return unrealizedGain;
    }

    public void setUnrealizedGain(double unrealizedGain) {
        this.unrealizedGain = unrealizedGain;
    }
}
