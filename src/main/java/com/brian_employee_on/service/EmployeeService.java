package com.brian_employee_on.service;

import com.brian_employee_on.EmployeeStatus;
import com.brian_employee_on.dto.EmployeeDetails;
import com.brian_employee_on.dto.EmployeeRegistration;
import com.brian_employee_on.model.Employee;
import com.brian_employee_on.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    //onboard or create employee
    public Employee registerEmployee(EmployeeRegistration employeeRegistration) {
        Employee employee = new Employee();
        employee.setFirstname(employeeRegistration.getFirstname());
        employee.setLastname(employeeRegistration.getLastname());
        employee.setDepartment(employeeRegistration.getDepartment());
        employee.setPosition(employeeRegistration.getPosition());
        employee.setEmail(employeeRegistration.getEmail());
        employee.setPhonenumber(employeeRegistration.getPhonenumber());

        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    //edit employee information. verified employees' records cannot be updated. modify the controller as well
    public Employee updateEmployee(Integer id, EmployeeDetails employeeDetails) {
        Employee employee = getEmployeeById(id);
        if (employee.getStatus() == EmployeeStatus.VERIFIED) {
            throw new IllegalStateException("Verified employees cannot be edited.");
        }

        employee.setFirstname(employeeDetails.getFirstname());
        employee.setLastname(employeeDetails.getLastname());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setPosition(employeeDetails.getPosition());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhonenumber(employeeDetails.getPhonenumber());

        employee.setStatus(EmployeeStatus.PENDING_VERIFICATION);


        return employeeRepository.save(employee);
    }
    //delete employee
    /*
    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);    }*/

    //to ensure that verified employee can not be deleted
    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employee.getStatus() == EmployeeStatus.VERIFIED) {
            throw new IllegalStateException("Verified employees cannot be deleted.");
        }

        employeeRepository.deleteById(id);
    }


    //get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    //verify employee method

    public Employee verifyEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employee.getStatus() == EmployeeStatus.VERIFIED) {
            throw new IllegalStateException("Employee is already verified.");
        }

        employee.setStatus(EmployeeStatus.VERIFIED);
        return employeeRepository.save(employee);
    }
    public List<Employee> getEmployeeByStatus(EmployeeStatus status){
        return employeeRepository.findByStatus(status);
    }
}

