package com.example.apachebeam.wordcount.mapper;

import com.example.apachebeam.wordcount.model.Account;
import com.example.apachebeam.wordcount.model.LoanAccount;
import com.example.apachebeam.wordcount.model.Result;

public class ResultMapper {
    public static void buildResultFromAccount(Result result, Account account) {
        result.setAccountNo(account.getAccountNo());
        result.setFirstName(account.getFirstName());
        result.setBalance(account.getBalance());
    }

    public static void buildResultFromLoanAccount(Result result, LoanAccount loanAccount) {
        result.setLoanAccountNo(loanAccount.getLoanAccountNo());
        result.setLoanOutstanding(loanAccount.getLoanOutstanding());
        result.setLoanAmount(loanAccount.getLoanAmount());
    }
}
