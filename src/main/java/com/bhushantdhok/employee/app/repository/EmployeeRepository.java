package com.bhushantdhok.employee.app.repository;

import com.bhushantdhok.employee.app.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeRepository {
    @Autowired
    private MongoTemplate template;
    public List<Employee> getEmplyeeByDeparment(String departmentName){
        Query query = new Query(Criteria.where("department.name").is(departmentName));
        return template.find(query, Employee.class);
    }

    public List<Employee> getEmployeesByCity(String cityName){
        Query query = new Query(Criteria.where("city").is(cityName));
        return template.find(query, Employee.class);
    }

    public Employee save(Employee employee) {
        return template.save(employee);
    }

    public Employee getEmplyeeById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return template.findOne(query, Employee.class);
    }
}
