package com.example.apachebeam.wordcount;

import com.example.apachebeam.wordcount.runner.PipelineRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MergerApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(MergerApplication.class, args);
        PipelineRunner.runPipeline();
    }

}
