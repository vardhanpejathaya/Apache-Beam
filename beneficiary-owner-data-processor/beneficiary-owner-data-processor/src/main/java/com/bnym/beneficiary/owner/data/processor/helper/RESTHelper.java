package com.bnym.beneficiary.owner.data.processor.helper;

import com.bnym.beneficiary.owner.data.processor.model.Position;
import com.bnym.beneficiary.owner.data.processor.model.Result1Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RESTHelper {

    static RestTemplate restTemplate = new RestTemplate();

    public static List<Position> getPositions() throws JsonProcessingException {
        String response = restTemplate.getForObject("http://localhost:9090/position/all", String.class);
        return Arrays.asList(new ObjectMapper().readValue(response, Position[].class));
    }

    public static void postResult(Result1Map map) {
        restTemplate.postForObject("http://localhost:9090/position/print", map, Void.class);
    }
}

