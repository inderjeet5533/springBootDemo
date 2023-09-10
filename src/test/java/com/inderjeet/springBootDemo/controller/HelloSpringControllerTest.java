package com.inderjeet.springBootDemo.controller;

import com.inderjeet.springBootDemo.SpringBootDemoApplication;
import com.inderjeet.springBootDemo.TestHelper;
import com.inderjeet.springBootDemo.exception.CustomExceptionHandler;
import com.inderjeet.springBootDemo.exception.model.CustomErrorResponse;
import com.inderjeet.springBootDemo.model.FormData;
import com.inderjeet.springBootDemo.model.FormDataRes;
import com.inderjeet.springBootDemo.service.HelloSpringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringBootDemoApplication.class)
@WebAppConfiguration
class HelloSpringControllerTest extends TestHelper {

    public   MockMvc mvc;
    public HelloSpringService helloSpringService;

    @BeforeEach
    void setUp() {
        helloSpringService = Mockito.mock(HelloSpringService.class);
        HelloSpringController helloSpringController = new HelloSpringController(helloSpringService);
        mvc =  MockMvcBuilders.standaloneSetup(helloSpringController)
                .setControllerAdvice(new CustomExceptionHandler()) //This line is necessary if you want to call your handler in case of exception.
                .build();
    }

    @Test
    void getSuccessResponse_when_getHello_called() throws Exception {
        String uri = "/getHello";

        MvcResult mvcResult = mvc.perform(get(uri))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Hello SpringBoot", content);

    }

    @Test
    void getSuccessResponse_when_saveFormData_called() throws Exception {
        String uri = "/saveForm";
        FormData formData = getFormData();
        String formDataString = mapToJson(formData);
        Mockito.when(helloSpringService.saveFormData(any()))
                .thenReturn("Record Successfully Inserted with uniqueID : 1");

        MvcResult mvcResult = mvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(formDataString))
                .andExpect(status().isCreated())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Record Successfully Inserted with uniqueID : 1", content);

    }

    @Test
    void getSuccessResponse_when_getForm_called() throws Exception {
        String uri = "/getForm";
        FormDataRes formDataRes = getFormDataRes();
        String formDataResString = mapToJson(formDataRes);
        Mockito.when(helloSpringService.getFormData())
                .thenReturn(formDataRes);

        MvcResult mvcResult = mvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(formDataResString))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        FormDataRes res = mapFromJson(content, FormDataRes.class);
        assertEquals(1, res.getFormDataList().size());
        assertEquals("firstName", res.getFormDataList().get(0).getFirstName());

    }

    @Test
    void getBadRequest_when_saveFormDataCalled_withMissingFields() throws Exception {
        String uri = "/saveForm";
        FormData formData = getFormDataFirstNameMissing();
        String formDataString = mapToJson(formData);

        MvcResult mvcResult = mvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(formDataString))
                        .andExpect(status().isBadRequest())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        CustomErrorResponse errorRes = mapFromJson(content, CustomErrorResponse.class);
        assertEquals("Bad Request", errorRes.getDescription());
        assertEquals("firstName", errorRes.getErrors().get(0).getName());
        assertEquals("must not be blank", errorRes.getErrors().get(0).getMessage());

    }

}
