package com.example.orchestration_serivce.controllers;


import com.example.orchestration_serivce.custom_annotation.LogMethodParam;
import com.example.orchestration_serivce.exception.InvalidJsonException;
import com.example.orchestration_serivce.services.orchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private orchestrationService orchestrationService;


    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Up");
    }


    @PostMapping("/process")
    @LogMethodParam
    public ResponseEntity<String> process(@RequestBody Map<String, Object> payload) {


        if (!payload.containsKey("Name")) {
            throw new InvalidJsonException("Invalid JSON Name not received ");
        }
        if (!payload.containsKey("Surname")) {
            throw new InvalidJsonException("Invalid JSON surname  not received ");
        }

        String message = orchestrationService.makeHttpCalls(payload);

        return ResponseEntity.ok(message);
    }
}
