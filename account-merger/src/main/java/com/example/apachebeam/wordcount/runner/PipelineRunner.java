package com.example.apachebeam.wordcount.runner;

import com.example.apachebeam.wordcount.Function;
import com.example.apachebeam.wordcount.helper.RESTHelper;
import com.example.apachebeam.wordcount.model.Account;
import com.example.apachebeam.wordcount.model.LoanAccount;
import com.example.apachebeam.wordcount.model.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.join.CoGroupByKey;
import org.apache.beam.sdk.transforms.join.KeyedPCollectionTuple;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TupleTag;

import java.util.Objects;

public class PipelineRunner {

    public static void runPipeline() throws JsonProcessingException {

        Pipeline pipeline = Pipeline.create();

//        Convert csv data to KV
        PCollection<KV<String, Account>> accountData = pipeline
                .apply("Read Lines from File", TextIO.read().from("input/account data.csv"))
                .apply("Remove Header", Filter.by(row -> !row.startsWith("#")))
                .apply("Generating KV from Account Strings",
                        MapElements.via(Function.generateKVFromAccountFn()));

//        Convert rest api data to KV
        PCollection<KV<String, LoanAccount>> loanData = pipeline
                .apply("Create PCollection from List",
                        Create.of(RESTHelper.getLoanAccounts()))
                .apply("Generating KV from Loan Accounts",
                        MapElements.via(Function.generateKVFromLoanAccountFn()));

        final TupleTag<LoanAccount> loanTag = new TupleTag<>();
        final TupleTag<Account> accountTag = new TupleTag<>();

//        merge on basis of key - account no
        PCollection<Result> resultData = KeyedPCollectionTuple
                .of(accountTag, accountData)
                .and(loanTag, loanData)
                .apply(CoGroupByKey.create())
                .apply("Extracting Result from Account and LoanAccount",
                        ParDo.of(Function.ExtractResultFn(loanTag, accountTag)));

//        apply filter and write to file
        resultData
                .apply("Filter Extra Data from both Datasets",
                        Filter.by(a -> a.getAccountNo() != 0 && Objects.nonNull(a.getLoanAccountNo())))
                .apply("Parsing Result to Json", ParDo.of(Function.parseResultToJSONFn()))
                .apply("Writing to File", TextIO.write().to("output/Result").withoutSharding());

        pipeline.run().waitUntilFinish();

    }
}
