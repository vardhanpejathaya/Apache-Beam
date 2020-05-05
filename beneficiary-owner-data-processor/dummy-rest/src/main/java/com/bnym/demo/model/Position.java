package com.bnym.demo.model;

import java.io.Serializable;

public class Position implements Serializable {
    private String assetType;
    private long accountNo;
    private long quantity;
    private String exchange;
    private String isin;
    private double unrealizedGain;

    public Position(String assetType, long accountNo, long quantity, String exchange, String isin, double unrealizedGain) {
        this.assetType = assetType;
        this.accountNo = accountNo;
        this.quantity = quantity;
        this.exchange = exchange;
        this.isin = isin;
        this.unrealizedGain = unrealizedGain;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
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
        return "{" +
                "assetType:'" + assetType + '\'' +
//                ", accountNo:" + accountNo +
                ", quantity:" + quantity +
//                ", exchange:'" + exchange + '\'' +
                ", isin:'" + isin + '\'' +
                ", unrealizedGain:" + unrealizedGain +
                '}';
    }
}
