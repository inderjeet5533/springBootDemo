package com.inderjeet.springBootDemo.service;

import com.inderjeet.springBootDemo.TestHelper;
import com.inderjeet.springBootDemo.controller.HelloSpringController;
import com.inderjeet.springBootDemo.entity.FormDataEntity;
import com.inderjeet.springBootDemo.model.FormData;
import com.inderjeet.springBootDemo.model.FormDataRes;
import com.inderjeet.springBootDemo.repository.FormDataRepository;
import com.inderjeet.springBootDemo.service.helper.HelloSpringServiceHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class HelloSpringServiceTest extends TestHelper {

    private FormDataRepository formDataRepository;
    private HelloSpringServiceHelper helloSpringServiceHelper;
    private HelloSpringService helloSpringService;

    @BeforeEach
    void setUp() {
        formDataRepository = Mockito.mock(FormDataRepository.class);
        helloSpringServiceHelper = new HelloSpringServiceHelper();
        helloSpringService = new HelloSpringService(formDataRepository, helloSpringServiceHelper);
    }

    @Test
    void saveFormDataTest() {
        FormData formData = getFormData();
        FormDataEntity formDataEntity = getFormDataEntity();

        when(formDataRepository.save(any())).thenReturn(formDataEntity);
        String res =  helloSpringService.saveFormData(formData);

        assertEquals("Record Successfully Inserted with uniqueID : 1", res);

    }

    @Test
    void getFormDataTest() {
        List<FormDataEntity> formDataEntity = getAllFormDataEntity();

        when(formDataRepository.findAll()).thenReturn(formDataEntity);
        FormDataRes res =  helloSpringService.getFormData();

        assertEquals(1, res.getFormDataList().size());
        assertEquals(1, res.getFormDataList().get(0).getFormId());
        assertEquals("firstName", res.getFormDataList().get(0).getFirstName());
        assertEquals("lastName", res.getFormDataList().get(0).getLastName());
    }
}