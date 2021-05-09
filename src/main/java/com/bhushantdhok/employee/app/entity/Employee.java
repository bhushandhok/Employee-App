package com.bhushantdhok.employee.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employee")
public class Employee {
    @Id
    private String id;
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
    @NotBlank
    private String city;
    private int age;
    private Double salary;
    @NotNull
    private Department  department;

}
