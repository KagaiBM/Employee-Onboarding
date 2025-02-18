package com.brian_employee_on.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class ApiResponseEmployee {
    private boolean success;
    private String message;

    public ApiResponseEmployee(boolean b, String message) {

    }

    public boolean isSuccess() {
        return success;    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
