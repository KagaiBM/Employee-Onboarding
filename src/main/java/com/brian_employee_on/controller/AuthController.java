package com.brian_employee_on.controller;

import com.brian_employee_on.model.Admin;
import com.brian_employee_on.repository.AdminRepository;
import com.brian_employee_on.security.JwtUtil;
import com.brian_employee_on.service.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        adminRepository.save(admin);

        return ResponseEntity.ok().body(new ApiResponse(null, "Registration successful", HttpStatus.OK.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAdmin(@RequestBody Admin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok().body(new ApiResponse(token,"login successful", HttpStatus.OK.value()));
    }
}

