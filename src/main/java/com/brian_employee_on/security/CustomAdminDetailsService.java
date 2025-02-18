package com.brian_employee_on.security;

import com.brian_employee_on.model.Admin;
import com.brian_employee_on.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomAdminDetailsService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(),
                List.of());

    }
}
