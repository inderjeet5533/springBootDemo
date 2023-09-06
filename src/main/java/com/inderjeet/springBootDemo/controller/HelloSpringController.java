package com.inderjeet.springBootDemo.controller;

import com.inderjeet.springBootDemo.model.FormData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class HelloSpringController {

    @GetMapping("/getHello")
    public ResponseEntity<String> getHelloWebFlux(){

        return ResponseEntity.ok("Hello SpringBoot");
    }

    @PostMapping(value = "/saveForm", consumes = "application/json")
    public ResponseEntity<String> saveFormData(@Valid @RequestBody FormData formData){

        return ResponseEntity.ok("Hello "+formData.getFirstName()+" from SpringBoot");
    }

}
