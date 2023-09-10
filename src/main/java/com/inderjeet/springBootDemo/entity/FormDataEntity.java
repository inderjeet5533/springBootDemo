package com.inderjeet.springBootDemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FORMDATA")
public class FormDataEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // this is required if form_id AUTO_INCREMENT PRIMARY KEY, without this will throw exception
    @Column(name = "form_id")
    private int formId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;
    @Column
    private String contact;

}
