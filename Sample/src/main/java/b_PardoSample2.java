import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PCollection;

import java.util.ArrayList;
public class b_PardoSample2 {

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
        Pipeline p = Pipeline.create(options);

        // Create a PCollection from static objects

        // Create a PCollection from static objects
        ArrayList< String > strs = new ArrayList < > ();
        strs.add("Neil");
        strs.add("John");
        strs.add("Bob");

        // The input PCollection.
        PCollection<String> words = p.apply(Create.of(strs));

        // Apply a ParDo with an anonymous DoFn to the PCollection words.
        // Save the result as the PCollection wordLengths.
        PCollection<Integer> wordLengths = words.apply(
                ParDo
                        .of(new DoFn<String, Integer>() {       // a DoFn as an anonymous inner class instance
                            @ProcessElement
                            public void processElement(ProcessContext c) {
                                c.output(c.element().length());
                                System.out.println("length of "+c.element()+" is "+c.element().length());

                            }
                        }));

        System.out.println("About to run!");

        p.run().waitUntilFinish();

        System.out.println("Run complete!");
    }

}
