package com.example.apachebeam.wordcount.helper;

import com.example.apachebeam.wordcount.model.LoanAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RESTHelper {

    static RestTemplate restTemplate = new RestTemplate();

    public static List<LoanAccount> getLoanAccounts() throws JsonProcessingException {
        String response = restTemplate.getForObject("http://localhost:9090/loan/all", String.class);
        return Arrays.asList(new ObjectMapper().readValue(response, LoanAccount[].class));
    }
}
