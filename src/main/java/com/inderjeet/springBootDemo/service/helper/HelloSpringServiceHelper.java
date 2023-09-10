package com.inderjeet.springBootDemo.service.helper;

import com.inderjeet.springBootDemo.entity.FormDataEntity;
import com.inderjeet.springBootDemo.model.FormData;
import com.inderjeet.springBootDemo.model.FormDataRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class HelloSpringServiceHelper {

    public FormDataEntity formDataObjToEntity(FormData formData) {
        log.info("formDataObjToEntity called from HelloSpringServiceHelper");
        return FormDataEntity.builder()
                .firstName(formData.getFirstName())
                .lastName(formData.getLastName())
                .email(formData.getEmail())
                .contact(formData.getContact())
                .build();
    }

    public FormDataRes formDataEntityToObj(List<FormDataEntity> formDataEntityList) {
        log.info("formDataEntityToObj called from HelloSpringServiceHelper");
        return FormDataRes.builder()
                .formDataList(formDataEntityList.stream()
                        .map(this::getFormData)
                        .collect(Collectors.toList()))
                .build();
    }

    private FormData getFormData(FormDataEntity e) {
        log.info("getFormData called from HelloSpringServiceHelper");
        return FormData.builder()
                .formId(e.getFormId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .email(e.getEmail())
                .contact(e.getContact())
                .build();
    }
}
