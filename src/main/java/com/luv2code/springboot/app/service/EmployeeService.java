package com.luv2code.springboot.app.service;//package com.luv2code.springboot.crud.service;

import com.luv2code.springboot.app.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();
    public Employee findById(int theId);
    public void save(Employee theEmployee);
    public void deleteById(int theId);
}
