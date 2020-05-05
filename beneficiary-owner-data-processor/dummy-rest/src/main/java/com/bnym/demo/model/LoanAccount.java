package com.bnym.demo.model;

import java.io.Serializable;

public class LoanAccount implements Serializable {

    private int accountNo;
    private String loanAccountNo;
    private int loanAmount;
    private int loanOutstanding;
    private String loanStartDate;
    private String loanEndDate;

    public LoanAccount(int accountNo, String loanAccountNo, int loanAmount, int loanOutstanding, String loanStartDate, String loanEndDate) {
        this.accountNo = accountNo;
        this.loanAccountNo = loanAccountNo;
        this.loanAmount = loanAmount;
        this.loanOutstanding = loanOutstanding;
        this.loanStartDate = loanStartDate;
        this.loanEndDate = loanEndDate;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getLoanAccountNo() {
        return loanAccountNo;
    }

    public void setLoanAccountNo(String loanAccountNo) {
        this.loanAccountNo = loanAccountNo;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanOutstanding() {
        return loanOutstanding;
    }

    public void setLoanOutstanding(int loanOutstanding) {
        this.loanOutstanding = loanOutstanding;
    }

    public String getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(String loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public String getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(String loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    @Override
    public String toString() {
        return "LoanAccount{" +
                "accountNo=" + accountNo +
                ", loanAccountNo='" + loanAccountNo + '\'' +
                ", loanAmount=" + loanAmount +
                ", loanOutstanding=" + loanOutstanding +
                ", loanStartDate='" + loanStartDate + '\'' +
                ", loanEndDate='" + loanEndDate + '\'' +
                '}';
    }
}
