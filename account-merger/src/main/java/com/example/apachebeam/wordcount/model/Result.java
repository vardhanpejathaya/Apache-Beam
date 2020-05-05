package com.example.apachebeam.wordcount.model;

import java.io.Serializable;
import java.util.StringJoiner;

public class Result implements Serializable {
    private int accountNo;
    private String firstName;
    private int balance;
    private String loanAccountNo;
    private int loanAmount;
    private int loanOutstanding;

    public Result() {
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        return joiner
                .add(Integer.toString(accountNo))
                .add(firstName)
                .add(Integer.toString(balance))
                .add(loanAccountNo)
                .add(Integer.toString(loanAmount))
                .add(Integer.toString(loanOutstanding))
                .toString();
    }
}
