package com.brian_employee_on.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
//create a constructor
public class EmployeeDetails {
    private String firstname;
    private String lastname;
    private String department;
    private String position;
    private Long phonenumber;
    private String email;

    public EmployeeDetails(String firstname, String lastname, String department, String position, Long phonenumber, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.position = position;
        this.phonenumber = phonenumber;
        this.email = email;
    }
    public String getFirstname() {
        return firstname;    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;    }

    public String getLastname() {
        return lastname;    }

    public void setLastname(String lastname) {
        this.lastname = lastname;    }

    public String getDepartment() {
        return department;    }

    public void setDepartment(String department) {
        this.department = department;    }

    public String getPosition() {
        return position;    }

    public void setPosition(String position) {
        this.position = position;    }

    public Long getPhonenumber() {
        return phonenumber;    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;    }

    public String getEmail() {
        return email;    }

    public void setEmail(String email) {
        this.email = email;    }
}
