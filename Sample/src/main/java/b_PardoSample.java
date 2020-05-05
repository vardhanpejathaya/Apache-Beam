/*
import org.apache.beam.sdk.Pipeline;
        import org.apache.beam.sdk.options.PipelineOptions;
        import org.apache.beam.sdk.options.PipelineOptionsFactory;
        import org.apache.beam.sdk.transforms.Create;
        import org.apache.beam.sdk.transforms.DoFn;
        import org.apache.beam.sdk.transforms.MapElements;
        import org.apache.beam.sdk.transforms.ParDo;
        import org.apache.beam.sdk.values.PCollection;
        import org.apache.beam.sdk.values.TypeDescriptor;

public class b_PardoSample {
    public static void main(String[] args) {

        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        Pipeline pipeline = Pipeline.create(options);

        // The input PCollection.
        PCollection<String> words = pipeline.apply(Create.of("ram", "krsishna"));

        // Apply a MapElements with an anonymous lambda function to the PCollection words.
        // Save the result as the PCollection wordLengths.
        PCollection<Integer> wordLengths = words.apply(
                MapElements.via((String word) -> word.length())
                        .withOutputType(new TypeDescriptor<Integer>() {

                        }));

    }
}
*/
