package com.brian_employee_on.repository;

import com.brian_employee_on.EmployeeStatus;
import com.brian_employee_on.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    //we modify this repository so that we are able to fetch employees based on their status
    //modify employeeservice by creating a method to fetch employees...
    //finally add a controller to fetch employees by status
    List<Employee> findByStatus(EmployeeStatus status);
  }
