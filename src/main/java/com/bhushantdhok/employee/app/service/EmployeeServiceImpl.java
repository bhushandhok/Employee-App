package com.bhushantdhok.employee.app.service;

import com.bhushantdhok.employee.app.entity.Employee;
import com.bhushantdhok.employee.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public String getEmployeeCountByDepartment(String departmentName) {
        String size = "0";
        List<Employee> employees = repository.getEmplyeeByDeparment(departmentName);
        if(employees != null && employees.size() > 0){
            size = String.valueOf(employees.size());
        }
        return size;
    }

    @Override
    public List<Employee> getEmployeesByCity(String cityName) {
        return repository.getEmployeesByCity(cityName);
    }

    @Override
    public List<Employee> employeeByDepartmentName(String departmentName) {
        return repository.getEmplyeeByDeparment(departmentName);
    }
}
