package com.luv2code.springboot.app.dao;

import com.luv2code.springboot.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    public List<Employee> findAllByOrderByLastNameAsc();
}
