package com.inderjeet.springBootDemo.repository;

import com.inderjeet.springBootDemo.entity.FormDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDataRepository extends JpaRepository<FormDataEntity, Integer> {

}
