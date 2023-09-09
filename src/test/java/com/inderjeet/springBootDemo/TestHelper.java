package com.inderjeet.springBootDemo;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inderjeet.springBootDemo.entity.FormDataEntity;
import com.inderjeet.springBootDemo.model.FormData;
import com.inderjeet.springBootDemo.model.FormDataRes;

import java.io.IOException;
import java.util.List;

public class TestHelper {

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    protected static FormData getFormData() {
        return FormData.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .contact("123456789")
                .build();
    }

    protected static FormData getFormDataFirstNameMissing() {
        return FormData.builder()
                .lastName("lastName")
                .email("email")
                .contact("123456789")
                .build();
    }

    protected static FormDataRes getFormDataRes() {
        return FormDataRes.builder()
                .formDataList(List.of(FormData.builder()
                        .formId(1)
                        .firstName("firstName")
                        .lastName("lastName")
                        .email("email")
                        .contact("123456789")
                        .build()))
                .build();
    }
    protected static FormDataEntity getFormDataEntity() {
        return FormDataEntity.builder()
                .formId(1)
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .contact("123456789")
                .build();
    }

    protected static List<FormDataEntity> getAllFormDataEntity() {
        return List.of(getFormDataEntity());
    }

}
