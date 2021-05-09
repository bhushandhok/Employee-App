package com.bhushantdhok.employee.app.controller;


import com.bhushantdhok.employee.app.entity.Employee;
import com.bhushantdhok.employee.app.exceptions.EmployeeNotFoundException;
import com.bhushantdhok.employee.app.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(EmployeeController.BASE_PATH)
public class EmployeeController {

    public static final String CITY_NAME = "/{cityName}";
    public static final String EMPLOYEE_BY_DEPARTMENT_NAME = "/employeeByDepartmentName/{departmentName}";
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    public static final String DEPARTMENT_NAME = "/department/{departmentName}";
    public static final String BASE_PATH = "employee";
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid Employee employee){
        logger.info("saving new Employee");
        Employee dbEmployee = service.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(dbEmployee);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {

        if(employee.getId() == null){
            throw new EmployeeNotFoundException("Please provide proper Employee ID to Update Employee");
        }
        logger.info("Updating Employee of id {}", employee.getId());
        Employee dbEmployee = service.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(dbEmployee);
    }

    @GetMapping(DEPARTMENT_NAME)
    public ResponseEntity<Map<?,?>> getEmployeeCount(@PathVariable @NotEmpty String departmentName){
        logger.info("getting Employee count by department Name= {}", departmentName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Collections.singletonMap("count", service.getEmployeeCountByDepartment(departmentName)));
    }

    @GetMapping(EMPLOYEE_BY_DEPARTMENT_NAME)
    public ResponseEntity<List<Employee>> getEmployeeListByDepartmentName(@PathVariable @Valid @NotEmpty String departmentName){
        logger.info("getting Employee List by department Name= {}", departmentName);
        List<Employee> employees = service.employeeByDepartmentName(departmentName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employees);
    }

    @GetMapping(CITY_NAME)
    public ResponseEntity<List<Employee>> getEmployyesByCity(@PathVariable @NotBlank String cityName){
        logger.info("Getting list of Employees by cityName = {}",cityName);
        List<Employee> employees = service.getEmployeesByCity(cityName);
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }
}
