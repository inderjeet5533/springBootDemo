package com.inderjeet.springBootDemo.service.helper;

import com.inderjeet.springBootDemo.entity.FormDataEntity;
import com.inderjeet.springBootDemo.model.FormData;
import org.springframework.stereotype.Component;

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
}
