package com.example.electriccomponentsshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/test")
public class TestController {
        @GetMapping("/all")
        public String allAccess() {
            return "Public Content.";
        }

        @GetMapping("/customer")
        @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_EMPLOYEE')")
        public String userAccess() {
            return "Customer Content.";
        }

        @GetMapping("/employee")
        @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
        public String moderatorAccess() {
            return "Employee Board.";
        }

        @GetMapping("/manager")
        @PreAuthorize("hasRole('ROLE_MANAGER')")
        public String adminAccess() {
            return "Manager Board.";
        }
    }

