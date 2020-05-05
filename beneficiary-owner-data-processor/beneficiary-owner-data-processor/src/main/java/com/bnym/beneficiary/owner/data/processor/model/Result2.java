package com.bnym.beneficiary.owner.data.processor.model;

import java.io.Serializable;
import java.util.StringJoiner;

public class Result2 implements Serializable {

    private long accountNo;
    private String assetType;
    private long quantity;
    private String exchange;
    private String isin;
    private double unrealizedGain;
    private String idOwner;
    private String boName;
    private String residentCountry;

    public Result2(String idOwner, String boName, String residentCountry, long accountNo, String assetType, long quantity, String exchange, String isin, double unrealizedGain) {
        this.idOwner = idOwner;
        this.boName = boName;
        this.residentCountry = residentCountry;
        this.accountNo = accountNo;
        this.assetType = assetType;
        this.quantity = quantity;
        this.exchange = exchange;
        this.isin = isin;
        this.unrealizedGain = unrealizedGain;
    }

    public Result2() {
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

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
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

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        return joiner.add(idOwner)
                .add(boName)
                .add(residentCountry)
                .add(Long.toString(accountNo))
                .add(exchange)
                .add(assetType)
                .add(Long.toString(quantity))
                .add(isin)
                .add(Double.toString(unrealizedGain))
                .toString();
    }
}
