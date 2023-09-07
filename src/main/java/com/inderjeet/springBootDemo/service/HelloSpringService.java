package com.inderjeet.springBootDemo.service;

import com.inderjeet.springBootDemo.entity.FormDataEntity;
import com.inderjeet.springBootDemo.model.FormData;
import com.inderjeet.springBootDemo.repository.FormDataRepository;
import com.inderjeet.springBootDemo.service.helper.HelloSpringServiceHelper;
import org.springframework.stereotype.Service;

@Service
public class HelloSpringService {

    private FormDataRepository formDataRepository;
    private HelloSpringServiceHelper helloSpringServiceHelper;

    public HelloSpringService(FormDataRepository formDataRepository, HelloSpringServiceHelper helloSpringServiceHelper){
        this.formDataRepository = formDataRepository;
        this.helloSpringServiceHelper = helloSpringServiceHelper;
    }

    public String saveFormData(FormData formData) {
        FormDataEntity formDataEntity = helloSpringServiceHelper.formDataObjToEntity(formData);
        FormDataEntity resEntity = formDataRepository.save(formDataEntity);
        return new StringBuilder("Record Successfully Inserted with uniqueID : ").append(resEntity.getFormId()).toString();
    }

}
