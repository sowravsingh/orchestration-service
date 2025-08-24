package com.example.orchestration_serivce.services;


import com.example.orchestration_serivce.custom_annotation.LogMethodParam;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
public class orchestrationService {


    private final RestTemplate restTemplate;

    public orchestrationService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @LogMethodParam
    public  String makeHttpCalls(Map<String,Object> payload){
        String traceId = UUID.randomUUID().toString();
        System.out.println(" trace id generated in main service is "+traceId);

        String hello = makeGreetCall(traceId);
        String concatinatedString= concatinateName(payload,traceId);

        return hello+" "+concatinatedString;
    }

    @LogMethodParam
    public  String makeGreetCall(String traceId){

        HttpHeaders headers = new HttpHeaders();
        headers.set("traceId", traceId);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> helloResponse = restTemplate.exchange(
                "http://localhost:8082/api/hello",
                HttpMethod.GET,
                entity,
                String.class
        );

       return helloResponse.getBody();
    }


    @LogMethodParam
    public  String concatinateName(Map<String,Object> payload,String traceId){

        HttpHeaders headers = new HttpHeaders();
        headers.set("traceId", traceId);

        HttpEntity<Map<String, Object>> postEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<String> nameResponse = restTemplate.exchange(
                "http://localhost:8083/api/concat",
                HttpMethod.POST,
                postEntity,
                String.class
        );
        String fullName = nameResponse.getBody();

        return fullName;
    }
}