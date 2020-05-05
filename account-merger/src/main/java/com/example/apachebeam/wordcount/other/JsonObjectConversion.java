package com.example.apachebeam.wordcount.other;

import com.example.apachebeam.wordcount.model.Car;
import com.google.gson.Gson;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

public class JsonObjectConversion {

    static Gson gson = new Gson();

    public static void convertJsonToObject() {
        Pipeline pipeline = Pipeline.create();
        PCollection<String> carData = pipeline
                .apply("ReadLines", TextIO.read().from("input/cars.json"));

        PCollection<Car> cars = carData
                .apply(ParDo.of(new ParseJSONStringToCarFn()));
        PCollection<Car> filteredCars = cars
                .apply(ParDo.of(new FilterOddCarFn()));
        PCollection<String> json = filteredCars
                .apply("", ParDo.of(new ParseCarToJSONStringFn())).setCoder(StringUtf8Coder.of());
        json.apply(TextIO.write().to("output/oddCar.json").withoutSharding());
        pipeline.run().waitUntilFinish();
    }

    static class ParseJSONStringToCarFn extends DoFn<String, Car> {
        @ProcessElement
        public void processElement(@Element String line, OutputReceiver<Car> out) {
            Car car = gson.fromJson(line, Car.class);
            out.output(car);
        }
    }

    static class FilterOddCarFn extends DoFn<Car, Car> {
        @ProcessElement
        public void processElement(@Element Car car, OutputReceiver<Car> out) {
            if (car.getId() % 2 != 0)
                out.output(car);
        }
    }

    static class ParseCarToJSONStringFn extends DoFn<Car, String> {
        @ProcessElement
        public void processElement(@Element Car car, OutputReceiver<String> out) {
            String json = gson.toJson(car);
            out.output(json);
        }
    }
}
