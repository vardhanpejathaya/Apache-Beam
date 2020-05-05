import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.PCollection;

import java.util.ArrayList;

public class pipeLineSample {

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
        Pipeline p = Pipeline.create(options);

        // Create a PCollection from static objects
        ArrayList< String > strs = new ArrayList < > ();
        strs.add("Neil");
        strs.add("John");
        strs.add("Bob");

        PCollection< String > pc1 = p.apply(Create.of(strs));
        PCollection < Long > count = pc1.apply(Count.globally());
        count.apply(MapElements.via(new MyMap()));

        System.out.println("About to run!");

        p.run().waitUntilFinish();

        System.out.println("Run complete!");
    }
}
class MyMap extends SimpleFunction< Long, Long > {
    public Long apply(Long in ) {
        System.out.println("Length is: " + in );
        return in;
    }
}