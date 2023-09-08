package com.inderjeet.springBootDemo.controller;

import com.inderjeet.springBootDemo.SpringBootDemoApplication;
import com.inderjeet.springBootDemo.TestHelper;
import com.inderjeet.springBootDemo.service.HelloSpringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringBootDemoApplication.class)
@WebAppConfiguration
class HelloSpringControllerTest extends TestHelper {

    public   MockMvc mvc;
    @Autowired
    public WebApplicationContext webApplicationContext;
    public HelloSpringService helloSpringService;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        helloSpringService = Mockito.mock(HelloSpringService.class);
        HelloSpringController helloSpringController = new HelloSpringController(helloSpringService);
    }

    @Test
    void getSuccessResponse_when_getHello_called() throws Exception {
        String uri = "/getHello";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Hello SpringBoot", content);

    }

    @Test
    void getSuccessResponse_when_saveFormData_called() throws Exception {
        String uri = "/saveFormData";

        Mockito.when(helloSpringService.saveFormData(getFormData()))
                .thenReturn("Record Successfully Inserted with uniqueID : 1");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(getFormData()))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Record Successfully Inserted with uniqueID : 1", content);

    }

}