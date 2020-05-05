package com.bnym.beneficiary.owner.data.processor;

import com.bnym.beneficiary.owner.data.processor.runner.PipelineRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeneficiaryOwnerDataProcessorApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(BeneficiaryOwnerDataProcessorApplication.class, args);
        PipelineRunner.runPipeline();
    }

}
