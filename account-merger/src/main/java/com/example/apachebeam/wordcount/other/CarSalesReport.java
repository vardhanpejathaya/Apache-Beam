package com.example.apachebeam.wordcount.other;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;

public class CarSalesReport {

    public static void generateCarSalesReport() {

        Pipeline pipeline = Pipeline.create();
        PCollection<String> cardData = pipeline.apply(
                "ReadLines", TextIO.read().from("input/car data.csv"))
                .apply("remove header", Filter.by((row -> !(row.startsWith("#")))));

        PCollection<KV<String, Integer>> parseAndConvertToKV
                = cardData.apply("Parsing", MapElements.via(new SimpleFunction<String, KV<String, Integer>>() {

            @Override
            public KV<String, Integer> apply(String cardData) {
                String[] split = cardData.split(",");
                return KV.of(split[1], Integer.valueOf(split[3]));
            }
        }));

        final PCollection<KV<String, Iterable<Integer>>> keyCollection
                = parseAndConvertToKV.apply(GroupByKey.create()); /// name:{1,2,2}

        final PCollection<String> sumKeyValue = keyCollection.apply("sum up", ParDo.of(
                new DoFn<KV<String, Iterable<Integer>>, String>() {

                    @ProcessElement
                    public void processElement(ProcessContext processContext) {
                        String brand = processContext.element().getKey();
                        Integer totalSale = 0;
                        Iterable<Integer> salesno = processContext.element().getValue();

                        for (Integer saleValue : salesno)
                            totalSale += saleValue;

                        processContext.output(brand + ":" + totalSale);
                    }

                }));

        sumKeyValue.apply(TextIO.write().to("output/car sale report.txt").withoutSharding());

        pipeline.run().waitUntilFinish();

    }
}
