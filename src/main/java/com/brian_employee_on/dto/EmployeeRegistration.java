package com.brian_employee_on.dto;

import com.brian_employee_on.EmployeeStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRegistration {

    private String firstname;
    private String lastname;
    private String department;
    private String position;
    private String phonenumber;
    private String email;

    @Enumerated(EnumType.STRING)  // Ensures stored values are "PENDING_VERIFICATION" or "VERIFIED"
    private EmployeeStatus status;
    //employee.SetStatus(EmployeeStatus.PENDING_VERIFICATION);

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }
    }
