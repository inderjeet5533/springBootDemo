package com.inderjeet.springBootDemo.controller;

import com.inderjeet.springBootDemo.model.FormData;
import com.inderjeet.springBootDemo.model.FormDataRes;
import com.inderjeet.springBootDemo.service.HelloSpringService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@Slf4j
public class HelloSpringController {

    private final HelloSpringService helloSpringService;

    public HelloSpringController(HelloSpringService helloSpringService){
        this.helloSpringService = helloSpringService;
    }

    @GetMapping("/getHello")
    public ResponseEntity<String> getHello(){
        log.info("getHello called from HelloSpringController");
        return ResponseEntity.ok("Hello SpringBoot");
    }

    @PostMapping(value = "/saveForm", consumes = "application/json")
    public ResponseEntity<String> saveFormData(@Valid @RequestBody FormData formData){
        log.info("saveFormData called from HelloSpringController");
        String res = helloSpringService.saveFormData(formData);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping(value = "/getForm", produces = "application/json")
    public ResponseEntity<FormDataRes> getFormData(){
        log.info("getFormData called from HelloSpringController");
        FormDataRes res = helloSpringService.getFormData();
        return ResponseEntity.ok(res);
    }

}
