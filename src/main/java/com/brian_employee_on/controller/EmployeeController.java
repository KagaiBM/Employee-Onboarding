package com.brian_employee_on.controller;

import com.brian_employee_on.EmployeeStatus;
import com.brian_employee_on.dto.EmployeeDetails;
import com.brian_employee_on.dto.EmployeeRegistration;
import com.brian_employee_on.model.Employee;
import com.brian_employee_on.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private EmployeeRegistration employeeRegistration;

    @PostMapping
    public ResponseEntity<Employee> registerEmployee(@RequestBody EmployeeRegistration employeeRegistration){
        Employee newEmployee = employeeService.registerEmployee(employeeRegistration);
        return ResponseEntity.ok(newEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDetails employeeDetails) {
        try{
            Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
            return ResponseEntity.ok(updatedEmployee); }
          catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
          catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    //used to verify an employee. a verified employee's details can neither be edited or deleted
    @PutMapping("/{id}/verify")
    public ResponseEntity<Employee> verifyEmployee(@PathVariable Integer id){
        Employee verifiedEmployee = employeeService.verifyEmployee(id);
        return ResponseEntity.ok(verifiedEmployee);
    }

  /**  @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
**/

    @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {     //add an exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Employee>> getEmployeeByStatus(@PathVariable String status){
        try{
            EmployeeStatus employeeStatus = EmployeeStatus.valueOf(status.toUpperCase()); //converts string to ENUM
            List<Employee> employees = employeeService.getEmployeeByStatus(employeeStatus);
            return ResponseEntity.ok(employees); }
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); }

        }
    }

