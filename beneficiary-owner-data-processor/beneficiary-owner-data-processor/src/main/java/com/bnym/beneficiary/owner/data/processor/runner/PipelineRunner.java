package com.bnym.beneficiary.owner.data.processor.runner;

import com.bnym.beneficiary.owner.data.processor.function.Function;
import com.bnym.beneficiary.owner.data.processor.helper.RESTHelper;
import com.bnym.beneficiary.owner.data.processor.model.Account;
import com.bnym.beneficiary.owner.data.processor.model.BeneficiaryOwner;
import com.bnym.beneficiary.owner.data.processor.model.Position;
import com.bnym.beneficiary.owner.data.processor.model.Result1;
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
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PipelineRunner {

    public static void runPipeline() throws JsonProcessingException {

        Pipeline pipeline = Pipeline.create();

        List<Position> positions = RESTHelper.getPositions();
        Map<Long, Map<String, List<Position>>> map = positions.stream()
                .collect(Collectors.groupingBy(
                        Position::getAccountNo,
                        Collectors.groupingBy(Position::getExchange)));

//        Convert rest api data to KV
        PCollection<KV<Long, Position>> positionKV = pipeline
                .apply("Create PCollection from List", Create.of(positions))
                .apply("Generating KV from Position",
                        MapElements.via(Function.generateKVFromPositionFn()));

//        Convert csv data to KV
        PCollection<KV<Long, Account>> accountKV = pipeline
                .apply("Read Lines from File", TextIO.read().from("input/account.csv"))
                .apply("Remove Header", Filter.by(row -> !row.startsWith("#")))
                .apply("Generating KV from Account Strings",
                        MapElements.via(Function.generateKVFromAccountFn()));

        final TupleTag<Position> positionTag = new TupleTag<>();
        final TupleTag<Account> accountTag = new TupleTag<>();

//        merge on basis of key - idOwner
        PCollection<Result1> result1Data = KeyedPCollectionTuple
                .of(accountTag, accountKV)
                .and(positionTag, positionKV)
                .apply(CoGroupByKey.create())
                .apply("Extracting Result1 from Position and Account",
                        ParDo.of(Function.extractResult1Fn(positionTag, accountTag,map)));

//        Convert csv data to KV
        PCollection<KV<String, BeneficiaryOwner>> beneficiaryOwnerKV = pipeline
                .apply("Read Lines from File", TextIO.read().from("input/beneficiaryowner.csv"))
                .apply("Remove Header", Filter.by(row -> !row.startsWith("#")))
                .apply("Generating KV from Beneficiary Owner Strings",
                        MapElements.via(Function.generateKVFromBeneficiaryOwnerFn()));

        final TupleTag<BeneficiaryOwner> beneficiaryOwnerTag = new TupleTag<>();
        final TupleTag<Result1> result1Tag = new TupleTag<>();

        PCollection<KV<String, Result1>> result1KV = result1Data
                .apply("Generating KV from Beneficiary Owner Strings",
                        MapElements.via(Function.generateKVFromResult1Fn()));

//        merge on basis of key - idOwner and print to csv
        KeyedPCollectionTuple
                .of(result1Tag, result1KV)
                .and(beneficiaryOwnerTag, beneficiaryOwnerKV)
                .apply(CoGroupByKey.create())
                .apply("Extracting Result2 from Result1 and BeneficiaryOwner",
                        ParDo.of(Function.extractResult2Fn(result1Tag, beneficiaryOwnerTag)))
                .apply("Parsing Result2 to CSV",
                        MapElements.into(TypeDescriptors.strings()).via(Object::toString))
                .apply("Writing to File", TextIO.write().to("output/Result2.txt").withoutSharding());

        pipeline.run().waitUntilFinish();
    }
}
