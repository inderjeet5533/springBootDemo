package com.inderjeet.springBootDemo.service.helper;

import com.inderjeet.springBootDemo.entity.FormDataEntity;
import com.inderjeet.springBootDemo.model.FormData;
import com.inderjeet.springBootDemo.model.FormDataRes;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HelloSpringServiceHelper {

    public FormDataEntity formDataObjToEntity(FormData formData) {
        return FormDataEntity.builder()
                .firstName(formData.getFirstName())
                .lastName(formData.getLastName())
                .email(formData.getEmail())
                .contact(formData.getContact())
                .build();
    }

    public FormDataRes formDataEntityToObj(List<FormDataEntity> formDataEntityList) {
        return FormDataRes.builder()
                .formDataList(formDataEntityList.stream()
                        .map(this::getFormData)
                        .collect(Collectors.toList()))
                .build();
    }

    private FormData getFormData(FormDataEntity e) {
        return FormData.builder()
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .email(e.getEmail())
                .contact(e.getContact())
                .build();
    }
}
