package com.example.electriccomponentsshop.controller;


import com.example.electriccomponentsshop.common.ERole;
import com.example.electriccomponentsshop.common.JwtUtils;
import com.example.electriccomponentsshop.dto.JwtResponse;
import com.example.electriccomponentsshop.dto.LoginRequest;
import com.example.electriccomponentsshop.dto.MessageResponse;
import com.example.electriccomponentsshop.dto.SignupRequest;
import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.entities.Role;
import com.example.electriccomponentsshop.repositories.AccountRepository;
import com.example.electriccomponentsshop.repositories.RoleRepository;
import com.example.electriccomponentsshop.services.AccountDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();
        List<String> roles = accountDetail.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, accountDetail.getId(), accountDetail.getEmail(), roles));
    }

    @PostMapping ("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest) {

        if (accountRepository.existsAccountByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("This email is registered"));
        } else {
            Account account = new Account(signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));
            Set<String> strRoles = signupRequest.getRoles();
            Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role accountRole = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
                roles.add(accountRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "employee":
                            Role roleEmp = roleRepository.findByRoleName(ERole.ROLE_EMPLOYEE).orElseThrow(() -> new RuntimeException("Role not found"));
                            roles.add(roleEmp);
                        case "manager":
                            Role roleManager = roleRepository.findByRoleName(ERole.ROLE_MANAGER).orElseThrow(() -> new RuntimeException("Role not found"));
                            roles.add(roleManager);
                        default:
                            Role roleCustomer = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
                            roles.add(roleCustomer);

                    }
                });
            }
            account.setRoles(roles);
            accountRepository.save(account);
            return ResponseEntity.ok().body(new MessageResponse("Register successfully"));
        }


    }
}

