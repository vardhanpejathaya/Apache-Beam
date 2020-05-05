package com.example.apachebeam.wordcount.other;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.Arrays;

public class WordCount {

    public static void countWords() {

        Pipeline pipeline = Pipeline.create();
        PCollection<String> cardData = pipeline.apply(
                "Read Lines", TextIO.read().from("input/words.txt"));

        final PCollection<String> extractWord = cardData.apply("Extract Word", FlatMapElements
                .into(TypeDescriptors.strings())
                .via((String line) -> Arrays.asList(line.split(" "))));

        extractWord.apply(Count.perElement())
                .apply("Format Results", MapElements
                        .into(TypeDescriptors.strings())
                        .via((KV<String, Long> wordCount) -> wordCount.getKey() + ": " + wordCount.getValue()))
                .apply(TextIO.write().to("output/word counts").withoutSharding());

        pipeline.run().waitUntilFinish();

    }


}
