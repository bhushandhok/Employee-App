package com.bhushantdhok.employee.app.service;

import com.bhushantdhok.employee.app.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    String getEmployeeCountByDepartment(String departmentName);


    List<Employee> getEmployeesByCity(String cityName);

    List<Employee> employeeByDepartmentName(String departmentName);
}
