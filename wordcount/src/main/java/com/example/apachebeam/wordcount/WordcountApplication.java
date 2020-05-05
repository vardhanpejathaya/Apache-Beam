package com.example.apachebeam.wordcount;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.transforms.join.CoGbkResult;
import org.apache.beam.sdk.transforms.join.CoGroupByKey;
import org.apache.beam.sdk.transforms.join.KeyedPCollectionTuple;
import org.apache.beam.sdk.values.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.lang.model.element.TypeElement;
import java.util.Arrays;

@SpringBootApplication
public class WordcountApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordcountApplication.class, args);

/*		Pipeline pipeline = Pipeline.create();
		PCollection<String> carddata = pipeline.apply(
				"ReadLines", TextIO.read().from("words.txt"));

		final PCollection<String> extract_wOrd = carddata.apply("Extract WOrd", FlatMapElements
				.into(TypeDescriptors.strings())
				.via((String line) -> Arrays.asList(line.split(" "))));

		extract_wOrd.apply(Count.<String>perElement())
				.apply("FormatResults", MapElements
						.into(TypeDescriptors.strings())
						.via((KV<String, Long> wordCount) -> wordCount.getKey() + ": " + wordCount.getValue()))
				.apply(TextIO.write().to("wordcounts").withoutSharding());

		System.out.println("This is sample application");


		pipeline.run().waitUntilFinish();*/


/*
		Pipeline pipeline = Pipeline.create();
		PCollection<String> carddata = pipeline.apply(
				"ReadLines", TextIO.read().from("cardatac.csv"))
				.apply("remove header", Filter.by((row->!(row.startsWith("#")))));

		PCollection<KV<String, Integer>> pasrseAncConvertToKV
				= carddata.apply("Parsing", MapElements.via(new SimpleFunction<String, KV<String, Integer>>() {

					@Override
					public  KV<String, Integer> apply(String carddata){
						String[] split = carddata.split(",");
						return KV.of(split[1],Integer.valueOf(split[3]));
					}
		}));

		final PCollection<KV<String, Iterable<Integer>>> keyCollection
				= pasrseAncConvertToKV.apply(GroupByKey.<String, Integer>create()); /// name:{1,2,2}

		final PCollection<String> sumKeyValue = keyCollection.apply("sumup", ParDo.of(
				new DoFn<KV<String, Iterable<Integer>>, String>() {

					@ProcessElement
					public void processElement(ProcessContext processContext) {
						String brand = processContext.element().getKey();
						Integer totalSale = 0;
						Iterable<Integer> salesno = processContext.element().getValue();

						for (Integer salevalue : salesno)
							totalSale += salevalue;

						processContext.output(brand + ":" + totalSale);
					}

				}));

		sumKeyValue.apply(TextIO.write().to("caralereport.txt").withoutSharding());

		pipeline.run().waitUntilFinish();
*/


		Pipeline pipeline = Pipeline.create();
		PCollection<String> accountdata = pipeline.apply(
				"ReadLines", TextIO.read().from("accountdata.csv"))
				.apply("remove header", Filter.by(row-> !row.startsWith("#")));

		PCollection<KV<String, String>> parseAccountDataToKV
				= accountdata.apply("Parsing", MapElements.via(new SimpleFunction<String, KV<String, String>>() {
			@Override
			public  KV<String, String> apply(String accdata){
				String[] split = accdata.split(",");
				return KV.of(split[0],split[0]+","+split[1]+","+split[2]+","+split[3]); ////"12131":"12131,Aliko,Dangote,2345"
			}
		}));


		PCollection<String> loandata = pipeline.apply(
				"ReadLines", TextIO.read().from("loandata.csv"))
				.apply("remove header", Filter.by(row-> !row.startsWith("#")));

		PCollection<KV<String, String>> parseLoanDataToKV
				= loandata.apply("Parsing", MapElements.via(new SimpleFunction<String, KV<String, String>>() {
			@Override
			public  KV<String, String> apply(String carddata){
				String[] split = carddata.split(",");
				return KV.of(split[0],split[1]+","+split[2]+","+split[3]); //12131:l1010,10000,50000
			}
		}));

		final TupleTag<String> loanDatTag = new TupleTag<String>();
		final TupleTag<String> accountDataTag = new TupleTag<String>();


		PCollection<KV<String, CoGbkResult>> joinedCollection =
				KeyedPCollectionTuple.of(accountDataTag, parseAccountDataToKV)
						.and(loanDatTag, parseLoanDataToKV)
						.apply(CoGroupByKey.<String>create())
				;

		PCollection<String> contactLines = joinedCollection.apply(ParDo.of(
				new DoFn<KV<String, CoGbkResult>, String>() {
					@ProcessElement
					public void processElement(ProcessContext c) {
						KV<String, CoGbkResult> e = c.element();
						String accountno = e.getKey();
						Iterable<String> loanDataItr = e.getValue().getAll(loanDatTag);
						Iterable<String> accountDataItr = e.getValue().getAll(accountDataTag);
						StringBuffer stringBuffer = new StringBuffer();
						for (String accountdata : accountDataItr)
							stringBuffer.append(accountdata);
						stringBuffer.append(",");
						for (String loandata : loanDataItr)
							stringBuffer.append(loandata);
						c.output(stringBuffer.toString());
					}
				}
		));

		contactLines.apply(TextIO.write().to("result.txt").withoutSharding());
		pipeline.run().waitUntilFinish();
	}

}
