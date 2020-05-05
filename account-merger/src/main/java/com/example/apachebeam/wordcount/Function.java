package com.example.apachebeam.wordcount;

import com.example.apachebeam.wordcount.mapper.AccountMapper;
import com.example.apachebeam.wordcount.mapper.ResultMapper;
import com.example.apachebeam.wordcount.model.Account;
import com.example.apachebeam.wordcount.model.LoanAccount;
import com.example.apachebeam.wordcount.model.Result;
import com.google.gson.Gson;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.transforms.join.CoGbkResult;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TupleTag;

public class Function {

    static Gson gson = new Gson();

    public static SimpleFunction<LoanAccount, KV<String, LoanAccount>> generateKVFromLoanAccountFn() {
        return new SimpleFunction<LoanAccount, KV<String, LoanAccount>>() {
            @Override
            public KV<String, LoanAccount> apply(LoanAccount loanAccount) {
                return KV.of(Integer.toString(loanAccount.getAccountNo()), loanAccount);
            }
        };
    }

    public static SimpleFunction<String, KV<String, Account>> generateKVFromAccountFn() {
        return new SimpleFunction<String, KV<String, Account>>() {
            @Override
            public KV<String, Account> apply(String accountData) {
                String[] split = accountData.split(",");
                return KV.of(split[0], AccountMapper.buildAccountFromString(split));
            }
        };
    }

    public static DoFn<KV<String, CoGbkResult>, Result> ExtractResultFn(TupleTag<LoanAccount> loanDataTag,
                                                                        TupleTag<Account> accountDataTag) {
        return new DoFn<KV<String, CoGbkResult>, Result>() {
            @ProcessElement
            public void processElement(ProcessContext c) {
                KV<String, CoGbkResult> e = c.element();
                Result result = new Result();
                e.getValue().getAll(accountDataTag).forEach(account ->
                        ResultMapper.buildResultFromAccount(result, account));
                e.getValue().getAll(loanDataTag).forEach(loanAccount ->
                        ResultMapper.buildResultFromLoanAccount(result, loanAccount));
                c.output(result);
            }
        };
    }

    public static DoFn<Result, String> parseResultToJSONFn() {
        return new DoFn<Result, String>() {
            @ProcessElement
            public void processElement(@Element Result result, OutputReceiver<String> out) {
                String json = gson.toJson(result);
                out.output(json);
            }
        };
    }
}


