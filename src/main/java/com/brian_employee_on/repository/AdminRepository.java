package com.brian_employee_on.repository;

import com.brian_employee_on.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository <Admin,Long>{
    Optional<Admin> findByUsername(String username);
}
